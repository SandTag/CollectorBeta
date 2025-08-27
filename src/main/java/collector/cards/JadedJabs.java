package collector.cards;

import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class JadedJabs extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(JadedJabs.class.getSimpleName());
    // intellij stuff attack, enemy, common, 10, 2, , , , 
    Shiv thisShiv = new Shiv();

    public JadedJabs() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 2;

        cardsToPreview = thisShiv;
        isPyre();
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void onPyred(AbstractCard card) {
        int toAdd = 0;
        if (card.tags.contains(expansionContentMod.KINDLING)) {
            toAdd = this.magicNumber;
        }
        int costResult = 0;
        if (card.cost == 0 && card.costForTurn == -1) {
            toAdd = EnergyPanel.getCurrentEnergy();
        }
        toAdd += costResult;
        int finalToAdd = toAdd;

        Shiv q = new Shiv();
        if (this.upgraded){
            q.upgrade();
        }

        DFL.atb(new LaterAction(()->{
            att(new MakeTempCardInHandAction(q, finalToAdd, true));
        }));
    }

    public void upp() {
        upgradeDamage(2);
//        upgradeMagicNumber(1);
        thisShiv.upgrade();
        uDesc();
    }
}
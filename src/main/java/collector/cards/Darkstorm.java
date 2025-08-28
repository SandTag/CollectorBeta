package collector.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.makeInHand;
import static utilityClasses.Wiz.shuffleIn;

public class Darkstorm extends AbstractCollectorCard {
    public final static String ID = makeID(Darkstorm.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , 4, 2

    public Darkstorm() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Blightning();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Blightning();
        makeInHand(q.makeStatEquivalentCopy(), magicNumber);
        DFL.atb(new MakeTempCardInDiscardAction(q.makeStatEquivalentCopy(), magicNumber));
        DFL.atb(new MakeTempCardInDrawPileAction(q.makeStatEquivalentCopy(), magicNumber, true, true, false));
//        shuffleIn(q, magicNumber);
    }

    public void upp() {
        //upgradeMagicNumber(1);
        upgradeBaseCost(1);
    }
}
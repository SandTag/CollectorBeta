package collector.cards;

import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;


public class Roast extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(Roast.class.getSimpleName());
    // intellij stuff attack, enemy, common, 9, 3, , , , 

    public Roast() {//Hello extremely overrated card.
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Ember();
        isPyre();
//        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        DFL.atb(new LaterAction(()->{
            if (pyredKindling) {
                makeInHand(new Ember(), magicNumber);
                dmg(m, AbstractGameAction.AttackEffect.FIRE);
            }else{
                dmg(m, AbstractGameAction.AttackEffect.FIRE);
            }
        }));
    }

    public void upp() {
        upgradeDamage(3);
        //upgradeMagicNumber(1);
    }

    boolean pyredKindling = false;
    @Override
    public void onPyred(AbstractCard card) {
        pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
    }

    @Override
    public void triggerOnGlowCheck() {//Common glowy effect for Fueled cards.
        if (AbstractDungeon.player != null && AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !DFL.pl().hand.isEmpty()) {
            if (DFL.pl().hand.group.stream().anyMatch(c -> c.tags.contains(expansionContentMod.KINDLING))) {
                this.glowColor = pyreOrange;
                return;
            }
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }
}
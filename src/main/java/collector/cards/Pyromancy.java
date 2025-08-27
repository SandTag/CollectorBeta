package collector.cards;

import collector.powers.CracklePower;
import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static collector.CollectorMod.makeID;

public class Pyromancy extends AbstractCollectorCard implements CollectorOrangeTextInterface {
    public final static String ID = makeID(Pyromancy.class.getSimpleName());
    // intellij stuff power, self, rare, , , , , , 

    public Pyromancy() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        //isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //DFL.atb(new LaterAction(()->{
        //    if (pyredKindling) {
        //        DFL.atb(new GainReservesAction(magicNumber));
        //    }
        //}));
        applyToSelf(new CracklePower(this.magicNumber));
    }

    public void upp() {
//        upgradeMagicNumber(1);
        upgradeBaseCost(1);
    }

//    boolean pyredKindling = false;
//    @Override
//    public void onPyred(AbstractCard card) {
//        pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
//    }

/*
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
    */
}
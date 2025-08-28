package collector.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.makeInHand;

public class Whomp extends AbstractCollectorCard {
    public final static String ID = makeID(Whomp.class.getSimpleName());
    // intellij stuff attack, enemy, rare, 25, 5, , , , 

    public Whomp() {//Now Torchbearer.
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
 //       baseDamage = 12;
        baseMagicNumber = magicNumber = 13;
        baseSecondMagic = secondMagic = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        atb(new AddTemporaryHPAction(p, p, magicNumber));
        makeInHand(new Ember(), magicNumber);
    }

    public void upp() {
        upgradeSecondMagic(1);
        upgradeMagicNumber(5);
    }
}
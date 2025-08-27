package collector.cards;

import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.animations.FastShakeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class Condemn extends AbstractCollectorCard {
    public final static String ID = makeID(Condemn.class.getSimpleName());
    // intellij stuff attack, enemy, common, 3, 1, , , 2, 

    public Condemn() {
        super(ID, 2, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new FastShakeAction(m, 0.3F, 0.5F));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        applyToEnemy(m, new DoomPower(m, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }
}
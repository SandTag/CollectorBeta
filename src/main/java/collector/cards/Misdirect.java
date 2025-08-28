package collector.cards;

import collector.powers.DoomPower;
import collector.powers.NextTurnReservePower;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class Misdirect extends AbstractCollectorCard {
    public final static String ID = makeID(Misdirect.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , , 

    public Misdirect() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = 3;
        this.magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AnimateJumpAction(p));
        blck();
//        applyToSelf(new NextTurnReservePower(1));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }
}
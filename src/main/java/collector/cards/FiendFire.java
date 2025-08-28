package collector.cards;

import collector.actions.kaboomAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import utilityClasses.DFL;
import static collector.CollectorMod.makeID;

public class FiendFire extends AbstractCollectorCard {
    public final static String ID = makeID(FiendFire.class.getSimpleName());

    public FiendFire() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.magicNumber = baseMagicNumber = 7;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new kaboomAction(magicNumber, m));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse){
            return false;
        }else if (DFL.pl().hand.size() < 2) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

    public void upp() {
//        this.selfRetain = true;
        upgradeMagicNumber(4);
        uDesc();
    }
}
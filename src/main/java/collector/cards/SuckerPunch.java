package collector.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;

public class SuckerPunch extends AbstractCollectorCard {
    public final static String ID = makeID(SuckerPunch.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 1

    public SuckerPunch() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.secondMagic >= 2){
            for (int i = 0; i < this.secondMagic; i++){
                dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
                applyToEnemy(m, new WeakPower(m, magicNumber, false));
            }
        }else{
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            applyToEnemy(m, new WeakPower(m, magicNumber, false));
        }
    }

    public void upp() {
        upgradeDamage(-2);
        upgradeSecondMagic(1);
    }
}
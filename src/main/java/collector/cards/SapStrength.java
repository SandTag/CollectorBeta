package collector.cards;

import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class SapStrength extends AbstractCollectorCard {
    public final static String ID = makeID(SapStrength.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 24, 8, , , , 

    public SapStrength() {//Current name - Doomed Strike.
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 5;
//        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.POISON);
        applyToEnemy(m, new DoomPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(2);
    }
}

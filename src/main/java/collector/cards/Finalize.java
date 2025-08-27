package collector.cards;

import collector.powers.DoomPower;
import collector.powers.HealIfDieThisTurnPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;

public class Finalize extends AbstractCollectorCard {
    public final static String ID = makeID(Finalize.class.getSimpleName());
    // intellij stuff skill, enemy, uncommon, , , , , 25, 5

    public Finalize() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 7;
        baseSecondMagic = secondMagic = 12;
        tags.add(CardTags.HEALING);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new HealIfDieThisTurnPower(m, magicNumber));
        applyToEnemy(m, new DoomPower(m, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(3);
        upgradeSecondMagic(4);
    }
}
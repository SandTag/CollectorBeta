package collector.cards;

import collector.powers.KarmaPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static collector.CollectorMod.makeID;


public class Karma extends AbstractCollectorCard {
    public final static String ID = makeID(Karma.class.getSimpleName());

    public Karma() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new KarmaPower(magicNumber));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}
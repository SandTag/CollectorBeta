package collector.cards;

import collector.powers.OmenPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToSelf;

public class Omen extends AbstractCollectorCard {
    public final static String ID = makeID(Omen.class.getSimpleName());
    // intellij stuff power, self, rare, , , , , 20, 5

    public Omen() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new OmenPower(magicNumber));
    }

    public void upp() {
        //upgradeMagicNumber(1);
        upgradeBaseCost(1);
        //isInnate = true;
        //uDesc();
    }
}
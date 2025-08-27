package collector.cards.collectibles;

import collector.powers.collectioncards.MushroomDamagePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class FungiBeastCard extends AbstractCollectibleCard {
    public final static String ID = makeID(FungiBeastCard.class.getSimpleName());
    // intellij stuff skill, enemy, common, , , , , 2, 1

    public FungiBeastCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 4;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        forAllMonstersLiving(q -> applyToEnemy(q, new VulnerablePower(q, this.magicNumber, false)));
        applyToSelf(new MushroomDamagePower(secondMagic));
    }

    public void upp() {
        upgradeSecondMagic(2);
    }
}
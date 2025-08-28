package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;

public class MawCard extends AbstractCollectibleCard {
    public final static String ID = makeID(MawCard.class.getSimpleName());
    // intellij stuff attack, enemy, common, 40, 10, , , , 

    public MawCard() {
        super(ID, 3, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        //baseDamage = 24;
        baseBlock = 13;
        baseMagicNumber = magicNumber = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new StrengthPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeBlock(5);
        upgradeMagicNumber(1);
    }
}
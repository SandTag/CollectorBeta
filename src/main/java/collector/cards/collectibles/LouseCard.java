package collector.cards.collectibles;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;
import utilityClasses.DFL;
import static collector.CollectorMod.makeID;

public class LouseCard extends AbstractCollectibleCard {
    public final static String ID = makeID(LouseCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , 7, 3, , 

    public LouseCard() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 3;
//        this.baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnExhaust() {
        //applyToSelf(new BlurPower(DFL.pl(), magicNumber));
        blck();
        applyToSelf(new NextTurnBlockPower(DFL.pl(), this.block));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeBlock(2);
//        upgradeMagicNumber(1);
    }
}
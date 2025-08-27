package collector.cards.collectibles;

import collector.powers.collectioncards.LouseCardPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;

public class TangerineCollectedCard extends AbstractCollectibleCard {
    public final static String ID = makeID(TangerineCollectedCard.class.getSimpleName());

    public TangerineCollectedCard() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseBlock = 4;
        baseSecondMagic = secondMagic = 5;
//        this.tags.add(SneckoMod.BANNEDFORSNECKO);
//        Will this cause issues? Maybe.
//        Will it be funny if snecko rolls this? Yes.
        tags.add(expansionContentMod.KINDLING);
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new BlurPower(p, magicNumber));
        applyToSelf(new LouseCardPower(secondMagic));
    }

    @Override
    public void triggerOnExhaust() {
        blck();
        applyToSelf(new BlurPower(DFL.pl(), magicNumber));
        applyToSelf(new LouseCardPower(secondMagic));
    }

    public void upp() {
        upgradeBlock(2);
//        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
//        uDesc();
    }
}

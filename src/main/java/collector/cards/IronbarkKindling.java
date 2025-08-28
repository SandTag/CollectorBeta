package collector.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;

import static collector.CollectorMod.makeID;

public class IronbarkKindling extends AbstractCollectorCard {
    public final static String ID = makeID(IronbarkKindling.class.getSimpleName());
    // intellij stuff skill, none, uncommon, , , , , 3, 2

    public IronbarkKindling() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = 9;
        this.selfRetain = true;
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnExhaust() {
        CardCrawlGame.sound.play("GUARDIAN_ROLL_UP");
        blck();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeBlock(3);
        uDesc();
    }
}
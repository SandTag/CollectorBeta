package collector.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class OakbrimKindling extends AbstractCollectorCard {
    public final static String ID = makeID(OakbrimKindling.class.getSimpleName());
    // intellij stuff skill, none, uncommon, , , , , 3, 2

    public OakbrimKindling() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnExhaust() {
        CardCrawlGame.sound.play("STAB_BOOK_DEATH");
        atb(new DrawCardAction(magicNumber));
        applyToSelf(new DrawCardNextTurnPower(AbstractDungeon.player, magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}
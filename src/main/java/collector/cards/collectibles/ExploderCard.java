package collector.cards.collectibles;

import collector.actions.DrawAllShapesFromCollectionAction;
import collector.cards.Ember;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.SHAPESWARM;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class ExploderCard extends AbstractCollectibleCard {
    public final static String ID = makeID(ExploderCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 3, 1

    public ExploderCard() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
//        baseDamage = 15;
        baseMagicNumber = magicNumber = 8;
        baseSecondMagic = secondMagic = 5;
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onRetained() {
        upgradeMagicNumber(this.secondMagic);
    }

    @Override
    public void triggerOnExhaust() {
//        for (int t = 0; t < magicNumber; t++) {
            atb(new DamageAllEnemiesAction(DFL.pl(), DamageInfo.createDamageMatrix(this.magicNumber), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
//        }
    }

    public void upp() {
        upgradeMagicNumber(3);
        upgradeSecondMagic(3);
    }
}
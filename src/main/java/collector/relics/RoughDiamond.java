package collector.relics;

import basemod.abstracts.CustomRelic;
import collector.CollectorMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.relics.Orrery;
import downfall.util.RareCardReward;
import downfall.util.TextureLoader;
import downfall.util.ThirdSealReward;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import sneckomod.util.ColorfulRareReward;

public class RoughDiamond extends CustomRelic {
    public static final String ID = CollectorMod.makeID(RoughDiamond.class.getSimpleName());
    private static final String IMG_PATH = RoughDiamond.class.getSimpleName() + ".png";
    private static final String OUTLINE_IMG_PATH = RoughDiamond.class.getSimpleName() + ".png";
    private boolean triggeredThisTurn = false;


    public RoughDiamond() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void onEquip() {
//        AbstractDungeon.getCurrRoom().rewards.add(new RareCardReward(AbstractDungeon.player.getCardColor()));
//        AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[1]);
//        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;
//        AbstractDungeon.combatRewardScreen.rewards.remove(AbstractDungeon.combatRewardScreen.rewards.size()-1);
    }

    @Override
    public int changeRareCardRewardChance(int rareCardChance) {
        return (rareCardChance * 2);
    }

    @Override
    public int changeUncommonCardRewardChance(int uncommonCardChance) {
        return Math.round(uncommonCardChance / 2.0f);
    }

    @Override
    public void onVictory() {
        this.grayscale = false;
    }

    public void atTurnStart() {
        this.triggeredThisTurn = false;
        this.grayscale = false;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
            if (card.rarity == AbstractCard.CardRarity.RARE) {
                if (!this.triggeredThisTurn) {
                    this.triggeredThisTurn = true;
                flash();
                this.grayscale = true;
                this.addToBot(new GainEnergyAction(1));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}


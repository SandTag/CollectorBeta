package collector.powers;

import basemod.helpers.CardModifierManager;
import collector.cardmods.ActuallyCollectedCardMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class OmenPower extends AbstractCollectorPower {
    public static final String NAME = "Omen";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public OmenPower(int amount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, amount);
    }

    public void onAfterCardPlayed(AbstractCard card) {
        if (CardModifierManager.hasModifier(card, ActuallyCollectedCardMod.ID)) {
            this.flash();
            applyToSelf(new ThornsPower(owner, amount));
        }
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
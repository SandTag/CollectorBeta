package collector.powers.collectioncards;

import collector.CollectorMod;
import collector.powers.AbstractCollectorPower;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.SlimeboundMod;

import java.util.Objects;

public class GremlinGangPower extends AbstractCollectorPower {
    public static final String NAME = "GremlinGangPower";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public static AbstractCard lastKnownGremlinCard;

    public GremlinGangPower(AbstractCard source) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, 1);
        lastKnownGremlinCard = source;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        //do nothing
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.hasTag(CollectorMod.GREMLINGANG) && !Objects.equals(card.name, lastKnownGremlinCard.name)){
            AbstractMonster m;
            flash();
            if (action.target != null) {
                m = (AbstractMonster) action.target;
            } else {
                m = (AbstractDungeon.getRandomMonster());
            }


            AbstractCard tmp = lastKnownGremlinCard.makeStatEquivalentCopy();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
            tmp.target_y = (Settings.HEIGHT / 2.0F);
            tmp.freeToPlayOnce = true;

            if (m != null) {
                tmp.calculateCardDamage(m);
            }

            tmp.purgeOnUse = true;
            tmp.applyPowers();
            AbstractDungeon.actionManager.cardQueue.add(new com.megacrit.cardcrawl.cards.CardQueueItem(tmp, m, card.energyOnUse));
            lastKnownGremlinCard = card;
            updateDescription();
        }

    }

    @Override
    public void updateDescription() {
        if (lastKnownGremlinCard != null) {
            description = DESCRIPTIONS[0] + lastKnownGremlinCard.name + DESCRIPTIONS[1];
        }
    }
}
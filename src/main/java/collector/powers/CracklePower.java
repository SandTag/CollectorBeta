package collector.powers;

import collector.actions.GainReservesAction;
import collector.actions.PyreAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

public class CracklePower extends AbstractCollectorPower implements OnPyrePower {
    public static final String NAME = "Crackle";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;
    private static AbstractGameAction lastActionCalled = null;

    public CracklePower(int addAmount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, addAmount);
    }

    int pyresThisTurn = 0;

    @Override
    public void atStartOfTurn() {
        pyresThisTurn = 0;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        DFL.atb(new LaterAction(() -> {
            flash();
            if (!AbstractDungeon.player.hand.isEmpty()) {
                PyreAction action = new PyreAction();
                lastActionCalled = action;
                addToTop(action);
            }
        }));
    }

    @Override
    public void onPyre(AbstractCard card) {
        if (pyresThisTurn < amount && !card.tags.contains(expansionContentMod.KINDLING)) {
            flash();
            pyresThisTurn++;
            DFL.atb(new GainReservesAction(1));
        }
    }

}
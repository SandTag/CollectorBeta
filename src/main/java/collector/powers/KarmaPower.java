package collector.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import utilityClasses.DFL;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.ArrayList;

public class KarmaPower extends AbstractCollectorPower {
    public static final String NAME = "Karma";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public KarmaPower(int amount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, amount);
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {

        ArrayList<String> debuffNames = new ArrayList<>();
        for (AbstractPower pow : DFL.pl().powers){
            if (!debuffNames.contains(pow.ID) && pow.type == PowerType.DEBUFF){
                debuffNames.add(pow.ID);
            }
        }

        for (AbstractMonster mon : DFL.activeMonsterList()) {
            for (AbstractPower pow : mon.powers) {
                if (!debuffNames.contains(pow.ID) && pow.type == PowerType.DEBUFF) {
                    debuffNames.add(pow.ID);
                }
            }
        }

        if (!debuffNames.isEmpty()) {
            addToBot(new GainBlockAction(owner, (debuffNames.size()*amount)));
        }

    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
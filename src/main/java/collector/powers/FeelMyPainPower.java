package collector.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import utilityClasses.DFL;

public class FeelMyPainPower extends AbstractCollectorPower {
    public static final String NAME = "FeelMyPain";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public FeelMyPainPower(int amount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, amount);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        AbstractCreature damageSource = this.owner;
        int damageAmount = this.amount;
        flash();
        for (AbstractMonster m : DFL.activeMonsterList()){
            addToBot(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.amount, true),
                    DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
            DFL.atb(new LoseHPAction(m, owner, this.amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
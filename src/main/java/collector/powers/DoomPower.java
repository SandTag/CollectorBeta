package collector.powers;

import collector.relics.JadeRing;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.isAfflicted;

public class DoomPower extends AbstractCollectorPower implements HealthBarRenderPower {
    public static final String NAME = "Doom";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.DEBUFF;
    public static final boolean TURN_BASED = false;

    // private boolean instakilled = false;

    public DoomPower(AbstractMonster target, int amount) {
        super(NAME, TYPE, TURN_BASED, target, null, amount);
        priority = 99;
    }

    @Override
    public int getHealthBarAmount() {

        int healthbar = 0;

        if(AbstractDungeon.player.hasRelic(JadeRing.ID)){
            healthbar = amount+6;
        }

        if(!(AbstractDungeon.player.hasRelic(JadeRing.ID))){
            healthbar = amount;
        }

        return healthbar;

    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            explode();
        }
    }// 70

    public void explode() {
        this.flashWithoutSound();
        System.out.println("DEBUG: Checking Affliction.");
        if (isAfflicted((AbstractMonster) this.owner)) {
            System.out.println("DEBUG: Affliction confirmed.");
        } else {
            if (this.owner.hasPower(DemisePower.POWER_ID)) {
                System.out.println("DEBUG: There is no Affliction. Reducing DemisePower by 1.");
                atb(new ReducePowerAction(this.owner, this.owner, DemisePower.POWER_ID, 1));
            } else {
                if (!this.owner.hasPower(WeakPower.POWER_ID)) {
                    if (this.amount < 2) {
                        System.out.println("DEBUG: Doom is 1 or 0 (?), removing... [Weak Step]");
                        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                    }else{
                        DFL.atb(new ReducePowerAction(this.owner, null, this, this.amount/2));
                    }
                }
                if (!this.owner.hasPower(VulnerablePower.POWER_ID)) {
                    if (this.amount < 2) {
                        System.out.println("DEBUG: Doom is 1 or 0 (?), removing... [Vulnerable Step]");
                        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                    }else{
                        DFL.atb(new LaterAction(()->{//Vuln step needs later action to prevent it queing up with the wrong amnt and removing 100% of doom which is not intended!
                            DFL.atb(new ReducePowerAction(this.owner, null, this, this.amount/2));
                        }));
                    }
                }
            }
        }

        if (AbstractDungeon.player.hasRelic(JadeRing.ID)) {
            if (amount+6 >= owner.currentHealth && (!this.owner.hasPower(IntangiblePower.POWER_ID) || this.owner.currentHealth == 1) && !this.owner.hasPower(BufferPower.POWER_ID)) {// Fixed bell noise on nemesis interaction.
                System.out.println("DEBUG: Kill SFX with Jade Ring.");
                CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
            }
        }

        if (!(AbstractDungeon.player.hasRelic(JadeRing.ID))) {
            if (amount >= owner.currentHealth && (!this.owner.hasPower(IntangiblePower.POWER_ID) || this.owner.currentHealth == 1) && !this.owner.hasPower(BufferPower.POWER_ID)) {
                System.out.println("DEBUG: Kill SFX without Jade Ring.");
                CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
            }
        }

        if (AbstractDungeon.player.hasRelic(JadeRing.ID)) {
            System.out.println("DEBUG: Dealing damage with Jade Ring.");
            this.addToBot(new LoseHPAction(owner, owner, amount+6, AbstractGameAction.AttackEffect.NONE));
        }

        if (!AbstractDungeon.player.hasRelic(JadeRing.ID)) {
            System.out.println("DEBUG: Dealing damage without Jade Ring.");
            this.addToBot(new LoseHPAction(owner, owner, amount, AbstractGameAction.AttackEffect.NONE));
        }

    }

    @Override
    public void updateDescription() {

        if (!(AbstractDungeon.player.hasRelic(JadeRing.ID))){
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }

        if(AbstractDungeon.player.hasRelic(JadeRing.ID)){
            int display = (this.amount+6);
            description = DESCRIPTIONS[0] + display + DESCRIPTIONS[1];
        }

    }

    @Override
    public Color getColor() {
        return Color.PURPLE.cpy();
    }
}
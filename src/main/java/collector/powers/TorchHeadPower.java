package collector.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import utilityClasses.DFL;

import static utilityClasses.Wiz.*;

public class TorchHeadPower extends AbstractCollectorPower implements NonStackablePower {
    public static final String NAME = "TorchHead";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    private int onAttackRandomDoom = 0;
    private int onAttackSmith = 0;
    private int onAttackBlock = 0;
    private int onAttackPoison = 0;
    private int onAttackDraw = 0;
    private int onAttackFlex = 0;

    public TorchHeadPower(int type, int toAdd) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, -1);
        switch (type) {
            case 0:
                onAttackRandomDoom += toAdd;
                break;
            case 1:
                onAttackSmith += toAdd;
                break;
            case 2:
                onAttackBlock += toAdd;
                break;
            case 3:
                onAttackPoison += toAdd;
                break;
            case 4:
                onAttackDraw += toAdd;
                break;
            case 5:
                onAttackFlex += toAdd;
                break;
            default:
                onAttackRandomDoom += toAdd;
                System.out.println("Incorrect value for torchhead call power! Should be 0-3");
                break;
        }
        updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            //Attack hit target that is not owner
            //Unblocked attack damage dealt
            //Owner has block or temp hp.

            if (onAttackRandomDoom > 0 || onAttackSmith > 0 || onAttackBlock > 0 || onAttackPoison > 0 || onAttackDraw > 0 || onAttackFlex > 0) {
                flash();
            }

            if (onAttackRandomDoom > 0) {
                addToBot(new ApplyPowerAction(target, DFL.pl(), new DoomPower((AbstractMonster)target, onAttackRandomDoom), onAttackRandomDoom));
            }

            if (onAttackSmith > 0) {
                for (int i = 0; i < onAttackSmith; i++) {
                    DFL.atb(new UpgradeRandomCardAction());
                }
                //addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(onAttackAOE, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            }

            if (onAttackBlock > 0) {
                atb(new GainBlockAction(owner, onAttackBlock));
            }

            if (onAttackPoison > 0) {
                addToBot(new ApplyPowerAction( target, DFL.pl(), new PoisonPower(target, AbstractDungeon.player, onAttackPoison), onAttackPoison));
            }

            if (onAttackDraw > 0) {
                atb(new ApplyPowerAction(DFL.pl(), DFL.pl(), new DrawCardNextTurnPower(DFL.pl(), onAttackDraw), onAttackDraw));
            }

            if (onAttackFlex > 0) {
                atb(new ApplyPowerAction(DFL.pl(), DFL.pl(), new StrengthPower(DFL.pl(), onAttackFlex), onAttackFlex));
                atb(new ApplyPowerAction(DFL.pl(), DFL.pl(), new LoseStrengthPower(DFL.pl(), onAttackFlex), onAttackFlex));
            }
        }

    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        if (onAttackRandomDoom > 0) {
            sb.append(DESCRIPTIONS[1] + onAttackRandomDoom + DESCRIPTIONS[2]);
            if (onAttackSmith > 0 || onAttackBlock > 0 || onAttackPoison > 0 || onAttackDraw > 0 || onAttackFlex > 0) {//If something ahead has a value, add a new line.
                sb.append(" NL ");
            }
        }
        if (onAttackPoison > 0) {
            sb.append(DESCRIPTIONS[1] + onAttackPoison + DESCRIPTIONS[7]);
            if (onAttackSmith > 0 || onAttackBlock > 0 || onAttackDraw > 0 || onAttackFlex > 0) {
                sb.append(" NL ");
            }
        }
        if (onAttackSmith > 0) {
            sb.append(DESCRIPTIONS[3] + onAttackSmith + DESCRIPTIONS[4]);
            if (onAttackBlock > 0 || onAttackDraw > 0 || onAttackFlex > 0) {
                sb.append(" NL ");
            }
        }
        if (onAttackBlock > 0) {
            sb.append(DESCRIPTIONS[5] + onAttackBlock + DESCRIPTIONS[6]);
            if (onAttackDraw > 0 || onAttackFlex > 0) {
                sb.append(" NL ");
            }
        }
        if (onAttackDraw > 0) {
            sb.append(DESCRIPTIONS[7] + onAttackDraw + (onAttackDraw == 1 ? DESCRIPTIONS[8] : DESCRIPTIONS[9]));
            if (onAttackFlex > 0) {
                sb.append(" NL ");
            }
        }
        if (onAttackFlex > 0) {
            sb.append(DESCRIPTIONS[10] + onAttackFlex + DESCRIPTIONS[11]);
        }
        description = sb.toString();
    }

    @Override
    public void stackPower(int stackAmount) {
    }

    @Override
    public boolean isStackable(AbstractPower power) {
        if (power instanceof TorchHeadPower) {
            this.onAttackRandomDoom += ((TorchHeadPower) power).onAttackRandomDoom;
            this.onAttackSmith += ((TorchHeadPower) power).onAttackSmith;
            this.onAttackBlock += ((TorchHeadPower) power).onAttackBlock;
            this.onAttackPoison += ((TorchHeadPower) power).onAttackPoison;
            this.onAttackDraw += ((TorchHeadPower) power).onAttackDraw;
            this.onAttackFlex += ((TorchHeadPower) power).onAttackFlex;
            updateDescription();
        }
        return true;
    }
}
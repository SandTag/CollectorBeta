package collector.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class SpiritLeech extends AbstractCollectorCard {
    public final static String ID = makeID(SpiritLeech.class.getSimpleName());
    // intellij stuff attack, enemy, common, 9, 3, , , 3, 2

    public SpiritLeech() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 14;
        baseMagicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new BiteEffect(m.hb.cX + MathUtils.random(-25.0F, 25.0F) * Settings.scale, m.hb.cY + MathUtils.random(-25.0F, 25.0F) * Settings.scale, Color.CHARTREUSE.cpy()), 0.0F);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        if (isAfflicted(m)) {//Shackles them.
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
            if (!m.hasPower("Artifact")) {
                addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, this.magicNumber), this.magicNumber));
            }
        }
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(3);
    }

    @Override
    public void triggerOnGlowCheck() {
        for (AbstractMonster m : getEnemies()) {
            if (m.hasPower(VulnerablePower.POWER_ID) && m.hasPower(WeakPower.POWER_ID)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
                return;
            }
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }
}
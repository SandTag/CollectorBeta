package collector.cards;

import collector.actions.GainReservesAction;
import collector.actions.LeechAction;
import collector.powers.DemisePower;
import collector.powers.NextTurnReservePower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class SpiritLeech extends AbstractCollectorCard {
    public final static String ID = makeID(SpiritLeech.class.getSimpleName());
    // intellij stuff attack, enemy, common, 9, 3, , , 3, 2

    public SpiritLeech() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new BiteEffect(m.hb.cX + MathUtils.random(-25.0F, 25.0F) * Settings.scale, m.hb.cY + MathUtils.random(-25.0F, 25.0F) * Settings.scale, Color.CHARTREUSE.cpy()), 0.0F);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        //if (upgraded) {
        //    DFL.atb(new GainReservesAction(magicNumber));
        //}else{
            applyToSelf(new NextTurnReservePower(magicNumber));
        //}
    }

    public void upp() {
        upgradeDamage(3);
        //upgradeMagicNumber(1);
        //uDesc();
    }

}
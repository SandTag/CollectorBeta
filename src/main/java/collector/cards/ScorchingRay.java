package collector.cards;

import automaton.actions.EasyXCostAction;
import collector.actions.ScorchingRayHitAction;
import collector.effects.ColoredVerticalAttackEffect;
import collector.powers.DoomPower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class ScorchingRay extends AbstractCollectorCard {
    public final static String ID = makeID(ScorchingRay.class.getSimpleName());
    // intellij stuff attack, enemy, common, 4, 1, , , 4,
    //Hello this card is overrated, thanks for coming to my ted talk.

    public ScorchingRay() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            atb(new VFXAction(new ColoredVerticalAttackEffect(m.hb.x + MathUtils.random(m.hb.width / 3, ((m.hb.width / 3) * 2)), m.hb.cY, true, new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1))));
            atb(new ScorchingRayHitAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
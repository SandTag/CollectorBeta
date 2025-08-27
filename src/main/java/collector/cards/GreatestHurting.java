package collector.cards;

import collector.effects.PurpleSearingBlowEffect;
import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class GreatestHurting extends AbstractCollectorCard {//Go to line 144 of AbstractCollectorCard
    public final static String ID = makeID(GreatestHurting.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 10, 2, , , 14, 2

    public GreatestHurting() {// Execute maleficence.
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int loop = 1;
        int targetLoss = m.hasPower(DoomPower.POWER_ID) ? m.getPower(DoomPower.POWER_ID).amount : 0;
        if (targetLoss > 0) {
            if (this.upgraded) {
                atb(new VFXAction(new PurpleSearingBlowEffect(m.hb.cX, m.hb.cY, 13)));
                loss(m, AbstractGameAction.AttackEffect.NONE, targetLoss);
                if (countDebuffs(m)) {
                    atb(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, 13)));
                    loss(m, AbstractGameAction.AttackEffect.NONE, targetLoss);
                }
            } else {
                atb(new VFXAction(new PurpleSearingBlowEffect(m.hb.cX, m.hb.cY, 13)));
                loss(m, AbstractGameAction.AttackEffect.NONE, targetLoss);
            }
        }
    }

    private boolean countDebuffs (AbstractMonster m){
        int count = 0;
        for (AbstractPower p : m.powers){
            if (p.type == AbstractPower.PowerType.DEBUFF){
                count ++;
                if (count >= 4){
                    return true;
                }
            }
        }
        return false;
    }

    public void upp() {
        uDesc();
    }
}
package collector.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

import java.util.ArrayList;

public class ScorchingRayHitAction extends AbstractGameAction {
    private final DamageInfo info;

    public ScorchingRayHitAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public void update() {
        if (shouldCancelAction()) {
            this.isDone = true;
            return;
        }
        tickDuration();
        if (this.isDone) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.FIRE, false));
            this.target.damage(this.info);
            if (this.target.lastDamageTaken > 0) {
                DFL.atb(new LaterAction(this::poofCard));
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            } else {
                addToTop(new WaitAction(0.1F));
            }
        }
    }

    private void poofCard(){
        ArrayList<AbstractCard> candidates = new ArrayList<>();
        for (AbstractCard c : DFL.pl().drawPile.group) {
            if (c.type.equals(AbstractCard.CardType.STATUS) || c.type.equals(AbstractCard.CardType.CURSE)) {
                candidates.add(c);
            }
        }
        for (AbstractCard c : DFL.pl().hand.group) {
            if (c.type.equals(AbstractCard.CardType.STATUS) || c.type.equals(AbstractCard.CardType.CURSE)) {
                candidates.add(c);
            }
        }
        for (AbstractCard c : DFL.pl().discardPile.group) {
            if (c.type.equals(AbstractCard.CardType.STATUS) || c.type.equals(AbstractCard.CardType.CURSE)) {
                candidates.add(c);
            }
        }

        if (!candidates.isEmpty()) {
            AbstractCard card;
            if (candidates.size() > 1) {
                card = candidates.get(AbstractDungeon.cardRandomRng.random(candidates.size() - 1));
            }else{
                card = candidates.get(0);
            }
            DFL.att(new ExhaustSpecificCardAction(card, DFL.pl().hand));
            DFL.att(new ExhaustSpecificCardAction(card, DFL.pl().drawPile));
            DFL.att(new ExhaustSpecificCardAction(card, DFL.pl().discardPile));
        }
    }

}

package collector.cards.collectibles;

import basemod.helpers.CardModifierManager;
import collector.cardmods.ActuallyCollectedCardMod;
import collector.cardmods.CollectedCardMod;
import collector.cards.SentryWave;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import sneckomod.SneckoMod;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class SentryCard extends AbstractCollectibleCard {
    public final static String ID = makeID(SentryCard.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 8, 2, , , 2, 1

    public SentryCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 12;
//        baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.exhaust = true;
        this.cardsToPreview = new SentryWave();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.3F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);

        //applyToEnemy(m, new WeakPower(m, magicNumber, false));
        //if (AbstractDungeon.player.hasPower(SentryPower.POWER_ID)) {
           // dmg(m, AbstractGameAction.AttackEffect.NONE);
//            applyToEnemy(m, new WeakPower(m, magicNumber, false));
           // atb(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower(SentryPower.POWER_ID)));
       // } else {
//applyToSelf(new SentryPower());
       // }

    }

    @Override
    public void triggerOnExhaust(){
        SentryWave card = new SentryWave();
        if (this.upgraded){
            card.upgrade();
        }
        CardModifierManager.addModifier(card, new CollectedCardMod());
        CardModifierManager.addModifier(card, new ActuallyCollectedCardMod());
        atb(new MakeTempCardInHandAction(card ,1));
    }

    public void upp() {
        upgradeDamage(2);
        cardsToPreview.upgrade();
        uDesc();
//        upgradeMagicNumber(1);
    }


    /*@Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(SentryPower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
            return;
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }*/
}
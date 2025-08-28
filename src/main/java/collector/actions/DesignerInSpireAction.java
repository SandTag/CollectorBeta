package collector.actions;
import collector.CollectorCollection;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import utilityClasses.DFL;

import java.util.ArrayList;

public class DesignerInSpireAction extends AbstractGameAction  {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("collector:DesignerInSpireAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p = DFL.pl();
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();
    private final boolean perma;

    public DesignerInSpireAction(boolean p) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        perma = p;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {



            for (AbstractCard c : this.p.hand.group) {//Checks for cards that have no upgrades
                if (!c.canUpgrade() || !DFL.pl().masterDeck.group.contains(c) && CollectorCollection.collection.group.contains(c)) {
                    this.cannotUpgrade.add(c);
                }
            }
            if (this.cannotUpgrade.size() == this.p.hand.group.size()) {//Nothing could be upgraded, return.
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {//Only one valid card.
                for (AbstractCard c : this.p.hand.group) {
                    if (c.canUpgrade()) {
                        c.upgrade();
                        c.superFlash();
                        c.applyPowers();
                        this.isDone = true;
                        if (perma){
                            for (AbstractCard cm : DFL.pl().masterDeck.group){//Non scuffed upgrade check.
                                if (cm.uuid == c.uuid){
                                    cm.upgrade();
                                    AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                                    AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(cm.makeStatEquivalentCopy()));
                                }
                            }
                        }
                        return;
                    }
                }
            }
            this.p.hand.group.removeAll(this.cannotUpgrade);




            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, true);
                tickDuration();
                return;
            }
            if (this.p.hand.group.size() == 1) {
                this.p.hand.getTopCard().upgrade();
                this.p.hand.getTopCard().superFlash();

                AbstractCard c = this.p.hand.getTopCard();
                if (perma) {
                    for (AbstractCard cm : DFL.pl().masterDeck.group) {//Non scuffed upgrade check.
                        if (cm.uuid == c.uuid) {
                            cm.upgrade();
                            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(cm.makeStatEquivalentCopy()));
                        }
                    }
                }

                returnCards();
                this.isDone = true;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                c.upgrade();
                c.superFlash();
                c.applyPowers();
                if (perma){
                    for (AbstractCard cm : DFL.pl().masterDeck.group){//Non scuffed upgrade check.
                        if (cm.uuid == c.uuid){
                            cm.upgrade();
                            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(cm.makeStatEquivalentCopy()));
                        }
                    }
                }
                this.p.hand.addToTop(c);
            }
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotUpgrade) {
            this.p.hand.addToTop(c);
        }
        this.p.hand.refreshHandLayout();
    }
}

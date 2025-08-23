package collector;

import basemod.helpers.CardModifierManager;
import champ.cards.IgnorePain;
import charbosses.bosses.Defect.CharBossDefect;
import charbosses.bosses.Hermit.CharBossHermit;
import charbosses.bosses.Ironclad.CharBossIronclad;
import charbosses.bosses.Merchant.CharBossMerchant;
import charbosses.bosses.Silent.CharBossSilent;
import charbosses.bosses.Watcher.CharBossWatcher;
import charbosses.monsters.*;
import collector.cardmods.CollectedCardMod;
import collector.cards.collectibles.*;
import collector.patches.CollectorBottleField;
import collector.relics.BagOfTricks;
import collector.ui.ExcessPileRemoveEffect;
import collector.util.CollectibleCardReward;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.colorless.Apparition;
import com.megacrit.cardcrawl.cards.colorless.Panacea;
import com.megacrit.cardcrawl.cards.colorless.SadisticNature;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.cards.purple.ConjureBlade;
import com.megacrit.cardcrawl.cards.purple.Omniscience;
import com.megacrit.cardcrawl.cards.purple.Ragnarok;
import com.megacrit.cardcrawl.cards.purple.TalkToTheHand;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.relics.FrozenEgg2;
import com.megacrit.cardcrawl.relics.MoltenEgg2;
import com.megacrit.cardcrawl.relics.ToxicEgg2;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import downfall.monsters.*;
import downfall.monsters.gauntletbosses.*;
import expansioncontent.cards.*;
import guardian.cards.AncientConstruct;
import guardian.cards.ConstructionForm;
import hermit.cards.Adapt;
import hermit.cards.FromBeyond;
import hermit.cards.Magnum;
import hermit.cards.ShadowCloak;
import sneckomod.cards.PureSnecko;
import utilityClasses.DFL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CollectorCollection {
    public static CardGroup collection;
    public static CardGroup combatCollection;
    public static HashMap<String, String> collectionPool;

    //Pools for modded acts.
    public static ArrayList<String> act1HallwayPool = new ArrayList<>();
    public static ArrayList<String> act1ElitePool = new ArrayList<>();
    public static ArrayList<String> act1BossPool = new ArrayList<>();

    public static ArrayList<String> act2HallwayPool = new ArrayList<>();
    public static ArrayList<String> act2ElitePool = new ArrayList<>();
    public static ArrayList<String> act2BossPool = new ArrayList<>();

    public static ArrayList<String> act3HallwayPool = new ArrayList<>();
    public static ArrayList<String> act3ElitePool = new ArrayList<>();
    public static ArrayList<String> act3BossPool = new ArrayList<>();

    public static ArrayList<String> act4PlusPool = new ArrayList<>();

    public static int MaxCollectionSize = 5;
    public static void testSize(){//5 normally, 7 with relic.
        MaxCollectionSize = 5;
        if (DFL.pl().hasRelic(BagOfTricks.ID)){
            MaxCollectionSize = 7;
        }
    }


    private static ArrayList<AbstractMonster> collectedAlready = new ArrayList<>();

    static {
        collectionPool = new HashMap<>();
        collectionPool.put(Cultist.ID, CultistCard.ID);
        collectionPool.put(LouseTangerine.ID, TangerineCollectedCard.ID);
        collectionPool.put(LouseNormal.ID, LouseCard.ID);
        collectionPool.put(LouseDefensive.ID, LouseCard.ID);
        collectionPool.put(AcidSlime_L.ID, AcidSlimeCard.ID);
        collectionPool.put(AcidSlime_M.ID, AcidSlimeCard.ID);
        collectionPool.put(AcidSlime_S.ID, AcidSlimeCard.ID);
        collectionPool.put(SpikeSlime_L.ID, SpikeSlimeCard.ID);
        collectionPool.put(SpikeSlime_M.ID, SpikeSlimeCard.ID);
        collectionPool.put(SpikeSlime_S.ID, SpikeSlimeCard.ID);
        collectionPool.put(GremlinThief.ID, SneakyGremlinCard.ID);
        collectionPool.put(GremlinTsundere.ID, ShieldGremlinCard.ID);
        collectionPool.put(GremlinWizard.ID, GremlinWizardCard.ID);
        collectionPool.put(GremlinFat.ID, FatGremlinCard.ID);
        collectionPool.put(GremlinWarrior.ID, MadGremlinCard.ID);
        collectionPool.put(JawWorm.ID, JawWormCard.ID);
        collectionPool.put(Looter.ID, ThievesCard.ID);
        collectionPool.put(LooterAlt.ID, ThievesCard.ID);
        collectionPool.put(Mugger.ID, ThievesCard.ID);
        collectionPool.put(MuggerAlt.ID, ThievesCard.ID);
        collectionPool.put(FungiBeast.ID, FungiBeastCard.ID);
        collectionPool.put(ShelledParasite.ID, ShelledParasiteCard.ID);
        collectionPool.put(SphericGuardian.ID, SphericGuardianCard.ID);
        collectionPool.put(Byrd.ID, ByrdCard.ID);
        collectionPool.put(Chosen.ID, ChosenCard.ID);
        collectionPool.put(SnakePlant.ID, SnakePlantCard.ID);
        collectionPool.put(Snecko.ID, PureSnecko.ID);
        collectionPool.put(Centurion.ID, CenturionCard.ID);
        collectionPool.put(Healer.ID, MysticCard.ID);
        collectionPool.put(Darkling.ID, DarklingsCard.ID);
        collectionPool.put(OrbWalker.ID, OrbWalkerCard.ID);
        collectionPool.put(Spiker.ID, SpikerCard.ID);
        collectionPool.put(Repulsor.ID, RepulsorCard.ID);
        collectionPool.put(Exploder.ID, ExploderCard.ID);
        collectionPool.put(Maw.ID, MawCard.ID);
        collectionPool.put(WrithingMass.ID, WrithingMassCard.ID);
        collectionPool.put(SpireGrowth.ID, SpireGrowthCard.ID);
        collectionPool.put(Sentry.ID, SentryCard.ID);
        collectionPool.put(GremlinNob.ID, GremlinNobCard.ID);
        collectionPool.put(Lagavulin.ID, LagavulinCard.ID);
        collectionPool.put(BookOfStabbing.ID, BookOfStabbingCard.ID);
        collectionPool.put(GremlinLeader.ID, GremlinLeaderCard.ID);
        collectionPool.put(SlaverBlue.ID, BlueSlaverCard.ID);
        collectionPool.put(SlaverRed.ID, RedSlaverCard.ID);
        collectionPool.put(Taskmaster.ID, TaskmasterCard.ID);
        collectionPool.put(BanditLeader.ID, RomeoCard.ID);
        collectionPool.put(BanditPointy.ID, PointyCard.ID);
        collectionPool.put(BanditBear.ID, BearCard.ID);
        collectionPool.put(GiantHead.ID, GiantHeadCardStageOne.ID);
        collectionPool.put(Nemesis.ID, NemesisCard.ID);
        collectionPool.put(SnakeDagger.ID, DaggerCard.ID);
        collectionPool.put(Transient.ID, TransientCard.ID);
        collectionPool.put(Reptomancer.ID, ReptomancerCard.ID);
        collectionPool.put(BronzeOrb.ID, BronzeOrbCard.ID);
        collectionPool.put(BronzeOrbWhoReallyLikesDefectForSomeReason.ID, BronzeOrbCard.ID);
        collectionPool.put(TorchHead.ID, TorchHeadCard.ID);
        collectionPool.put(MushroomPurple.ID, MushroomCard.ID);
        collectionPool.put(MushroomWhite.ID, MushroomCard.ID);
        collectionPool.put(MushroomRed.ID, MushroomCard.ID);
        collectionPool.put(FaceTrader.ID, FaceTraderCard.ID);
        collectionPool.put(ChangingTotem.ID, LivingWallCard.ID);
        collectionPool.put(ForgetfulTotem.ID, LivingWallCard.ID);
        collectionPool.put(GrowingTotem.ID, LivingWallCard.ID);
        collectionPool.put(Augmenter.ID, AugmenterCard.ID);
        collectionPool.put(FleeingMerchant.ID, MerchantCard.ID);
        collectionPool.put(CharBossMerchant.ID, MerchantCard.ID);
        collectionPool.put(Ironclad.ID, Inflame.ID);
        collectionPool.put(Silent.ID, Footwork.ID);
        collectionPool.put(Defect.ID, WhiteNoise.ID);
        collectionPool.put(Watcher.ID, TalkToTheHand.ID);
        collectionPool.put(Hermit.ID, ShadowCloak.ID);
        collectionPool.put(MirrorImageSilent.ID, Doppelganger.ID);
        collectionPool.put(Fortification.ID, SpireShieldCard.ID);
        collectionPool.put(LadyInBlue.ID, WomanInBlueCard.ID);
        collectionPool.put(SpireShield.ID, SpireShieldCard.ID);
        collectionPool.put(SpireSpear.ID, SpireSpearCard.ID);

        //Bosses
        collectionPool.put(NeowBossFinal.ID, FinalBossCard.ID);
        collectionPool.put(CorruptHeart.ID, BeatOfDeath.ID);
        collectionPool.put(SlimeBoss.ID, SuperPrepareCrush.ID);//There is an extra clause to make this drop.
        collectionPool.put(Hexaghost.ID, Hexaburn.ID);
        collectionPool.put(TheGuardian.ID, SuperBodyCrash.ID);
        collectionPool.put(BronzeAutomaton.ID, HyperBeam.ID);
        collectionPool.put(Champ.ID, LastStand.ID);
        collectionPool.put(TheCollector.ID, CollectorCard.ID);
        collectionPool.put(TimeEater.ID, Reverie.ID);
        collectionPool.put(AwakenedOne.ID, SuperBloodthirst.ID);
        collectionPool.put(Donu.ID, DoubleAct.ID);
        collectionPool.put(Deca.ID, DoubleAct.ID);

        //Apology slime?
        collectionPool.put(ApologySlime.ID, beginningCollectible.ID);//Apology slime isn't real, it cant hurt you.

        //Crossmod compat for bundles.
        //Act 1 Lot
        collectionPool.put("bundlecore:GellomaxStageBossCreature", Evolve.ID);
        collectionPool.put("bundlecore:GellomaxBlueCreature", Buffer.ID);
        collectionPool.put("bundlecore:GellomaxRedCreature", DemonForm.ID);
        collectionPool.put("bundlecore:GellomaxResidueCreature", BouncingFlask.ID);
        collectionPool.put("bundlecore:EldervullinMonkas", PhantasmalKiller.ID);

        //Act 2 Lot
        collectionPool.put("bundlecore:BigGothCollector", Alchemize.ID);
        collectionPool.put("bundlecore:RealChampionCreature", champ.cards.LastStand.ID);
        collectionPool.put("bundlecore:TitaniumAutomatonCreature", AncientConstruct.ID);
        collectionPool.put("bundlecore:StonksTorchhead", FireBreathing.ID);

        //Act 3 lot
        collectionPool.put("bundle_of_food:AngeryDonulet", Inflame.ID);
        collectionPool.put("bundlecore:Cursed_DecaFinalBoss", Entrench.ID);
        collectionPool.put("bundlecore:TimeussuyExpress", Chronoboost.ID);
        collectionPool.put("bundlecore:MightyMazalethSuperboss", DashGenerateEvil.ID);
        collectionPool.put("bundlecore:DiHexaGhost", Apparition.ID);

        //Visitors to the spire.
        collectionPool.put("bundlecore:PlanteraBossCreature", Envenom.ID);
        collectionPool.put("Bundle_Of_Terra:DukeFishronCreature", ShapePower.ID);
        collectionPool.put("Bundle_Of_Terra:NadoCreature", ChargeUp.ID);
        collectionPool.put("bundlecore:BeeeeegGuardian", ConstructionForm.ID);//A downfall boss fighting a boss from another mod that references a boss in downfall which drops a card from the playable character?
        collectionPool.put("bundlecore:SandTagBossCreature", "bundlecore:SandgunBossSpecial");

        //Bundles Endgame. - These may never actually exist depending on how things turn out with spire 2 and how long it takes for mod support, but you never know!
        collectionPool.put("bundlecore:SkunkAct5BossSecret", ShapePower.ID);
        collectionPool.put("bundlecore:GremlinKingAct5Boss", ShapePower.ID);
        collectionPool.put("bundlecore:BronzeMechAct5Boss", ShapePower.ID);
        collectionPool.put("bundlecore:SnarkFinalBossCreature", ShapePower.ID);
        collectionPool.put("bundlecore:NeowTrueFormCreature", ShapePower.ID);

        AssignToCollectedPools();
    }

    private static void AssignToCollectedPools() {
        //Sets up the act 1 -> 4+ collected pool fallbacks.

        //Act 1
        act1HallwayPool.add(CultistCard.ID);
        act1HallwayPool.add(LouseCard.ID);
        act1HallwayPool.add(AcidSlimeCard.ID);
        act1HallwayPool.add(SpikeSlimeCard.ID);
        act1HallwayPool.add(SneakyGremlinCard.ID);
        act1HallwayPool.add(ShieldGremlinCard.ID);
        act1HallwayPool.add(GremlinWizardCard.ID);
        act1HallwayPool.add(FatGremlinCard.ID);
        act1HallwayPool.add(MadGremlinCard.ID);
        act1HallwayPool.add(JawWormCard.ID);
        act1HallwayPool.add(RedSlaverCard.ID);
        act1HallwayPool.add(BlueSlaverCard.ID);
        act1ElitePool.add(GremlinNobCard.ID);
        act1ElitePool.add(LagavulinCard.ID);
        act1ElitePool.add(SentryCard.ID);
        act1BossPool.add(SuperPrepareCrush.ID);
        act1BossPool.add(Hexaburn.ID);
        act1BossPool.add(SuperBodyCrash.ID);
        act1BossPool.add(Immolate.ID);
        act1BossPool.add(CorpseExplosion.ID);
        act1BossPool.add(Buffer.ID);
        act1BossPool.add(ConjureBlade.ID);
        act1BossPool.add(Adapt.ID);

        //Act 2
        act2HallwayPool.add(ThievesCard.ID);
        act2HallwayPool.add(FungiBeastCard.ID);
        act2HallwayPool.add(ShelledParasiteCard.ID);
        act2HallwayPool.add(SphericGuardianCard.ID);
        act2HallwayPool.add(ByrdCard.ID);
        act2HallwayPool.add(ChosenCard.ID);
        act2HallwayPool.add(SnakePlantCard.ID);
        act2HallwayPool.add(SneckoCard.ID);
        act2HallwayPool.add(CenturionCard.ID);
        act2HallwayPool.add(MysticCard.ID);
        act2ElitePool.add(GremlinLeaderCard.ID);
        act2ElitePool.add(BookOfStabbingCard.ID);
        act2ElitePool.add(TaskmasterCard.ID);
        act2BossPool.add(TorchHeadCard.ID);
        act2BossPool.add(HyperBeam.ID);
        act2BossPool.add(LastStand.ID);
        act2BossPool.add(Reaper.ID);
        act2BossPool.add(AfterImage.ID);
        act2BossPool.add(Hyperbeam.ID);
        act2BossPool.add(Ragnarok.ID);
        act2BossPool.add(Magnum.ID);

        //Act 3
        act3HallwayPool.add(DarklingsCard.ID);
        act3HallwayPool.add(OrbWalkerCard.ID);
        act3HallwayPool.add(SpikerCard.ID);
        act3HallwayPool.add(RepulsorCard.ID);
        act3HallwayPool.add(ExploderCard.ID);
        act3HallwayPool.add(MawCard.ID);
        act3HallwayPool.add(WrithingMassCard.ID);
        act3HallwayPool.add(SpireGrowthCard.ID);
        act3HallwayPool.add(TransientCard.ID);
        act3ElitePool.add(GiantHeadCardStageOne.ID);
        act3ElitePool.add(NemesisCard.ID);
        act3ElitePool.add(ReptomancerCard.ID);
        act3BossPool.add(Reverie.ID);
        act3BossPool.add(SuperBloodthirst.ID);
        act3BossPool.add(DoubleAct.ID);
        act3BossPool.add(Barricade.ID);
        act3BossPool.add(StormOfSteel.ID);
        act3BossPool.add(Seek.ID);
        act3BossPool.add(Omniscience.ID);
        act3BossPool.add(FromBeyond.ID);

        //Act 4 - Its spicy!
        act4PlusPool.add(MerchantCard.ID);
        act4PlusPool.add(SpireShieldCard.ID);
        act4PlusPool.add(SpireSpearCard.ID);
        act4PlusPool.add(FinalBossCard.ID);
        act4PlusPool.add(BeatOfDeath.ID);
        act4PlusPool.add(EchoForm.ID);
        act4PlusPool.add(Omniscience.ID);
        act4PlusPool.add(Burst.ID);
        act4PlusPool.add(LimitBreak.ID);
        act4PlusPool.add(SadisticNature.ID);
    }

    public static AbstractCard getCollectedCard(AbstractMonster m) {
        AbstractCard returnValue;
        if (collectionPool.containsKey(m.id)) {
            returnValue = CardLibrary.getCopy(collectionPool.get(m.id));
        } else {
            if (m instanceof CharBossIronclad && ((CharBossIronclad) m).chosenArchetype != null) {
                switch (((CharBossIronclad) m).chosenArchetype.actNum) {
                    case 1:
                        returnValue = CardLibrary.getCopy(Immolate.ID);
                        break;
                    case 2:
                        returnValue = CardLibrary.getCopy(Reaper.ID);
                        break;
                    case 3:
                    default:
                        returnValue = CardLibrary.getCopy(Barricade.ID);
                        break;
                }
            } else if (m instanceof CharBossSilent && ((CharBossSilent) m).chosenArchetype != null) {
                switch (((CharBossSilent) m).chosenArchetype.actNum) {
                    case 1:
                        returnValue = CardLibrary.getCopy(CorpseExplosion.ID);
                        break;
                    case 2:
                        returnValue = CardLibrary.getCopy(AfterImage.ID);
                        break;
                    case 3:
                    default:
                        returnValue = CardLibrary.getCopy(StormOfSteel.ID);
                        break;
                }
            } else if (m instanceof CharBossDefect && ((CharBossDefect) m).chosenArchetype != null) {
                switch (((CharBossDefect) m).chosenArchetype.actNum) {
                    case 1:
                        returnValue = CardLibrary.getCopy(Buffer.ID);
                        break;
                    case 2:
                        returnValue = CardLibrary.getCopy(Hyperbeam.ID);
                        break;
                    case 3:
                    default:
                        returnValue = CardLibrary.getCopy(Seek.ID);
                        break;
                }
            } else if (m instanceof CharBossWatcher && ((CharBossWatcher) m).chosenArchetype != null) {
                switch (((CharBossWatcher) m).chosenArchetype.actNum) {
                    case 1:
                        returnValue = CardLibrary.getCopy(ConjureBlade.ID);
                        break;
                    case 2:
                        returnValue = CardLibrary.getCopy(Ragnarok.ID);
                        break;
                    case 3:
                    default:
                        returnValue = CardLibrary.getCopy(Omniscience.ID);
                        break;
                }
            } else if (m instanceof CharBossHermit && ((CharBossHermit) m).chosenArchetype != null) {
                switch (((CharBossHermit) m).chosenArchetype.actNum) {
                    case 1:
                        returnValue = CardLibrary.getCopy(Adapt.ID);
                        break;
                    case 2:
                        returnValue = CardLibrary.getCopy(Magnum.ID);
                        break;
                    case 3:
                    default:
                        returnValue = CardLibrary.getCopy(FromBeyond.ID);
                        break;
                }
            } else {//Fallbacks.
                if (!Objects.equals(AbstractDungeon.name, Exordium.NAME) &&
                        !Objects.equals(AbstractDungeon.name, TheCity.NAME) &&
                        !Objects.equals(AbstractDungeon.name, TheBeyond.NAME) &&
                        !Objects.equals(AbstractDungeon.name, TheEnding.NAME)){
                    return handleModdedActContext();
                }//Modded acts.
                else {// Unexpected enemy in a vanilla act -> placeholder collected card.
                    returnValue = new DefaultCollectibleCard();
                }
            }
        }
        CardModifierManager.addModifier(returnValue, new CollectedCardMod());
        return returnValue;
    }

    private static AbstractCard handleModdedActContext(){
        AbstractCard theCard;

        if (AbstractDungeon.actNum <= 1){//Act 1
            if (AbstractDungeon.getCurrRoom().monsters.monsters.stream().anyMatch(c -> c.type == AbstractMonster.EnemyType.BOSS)){
                theCard = (CardLibrary.getCopy(act1BossPool.get(AbstractDungeon.monsterHpRng.random(act1BossPool.size()-1))));
                //Extra card rewards uhh... use card rng? yikes.
                //This is much better and does not break deterministic seed order.
            }
            else if (AbstractDungeon.getCurrRoom().eliteTrigger){//After bosses, try elites.
                theCard = (CardLibrary.getCopy(act1ElitePool.get(AbstractDungeon.monsterHpRng.random(act1ElitePool.size()-1))));
            }else{//If neither, we are in a hallway.
                theCard = (CardLibrary.getCopy(act1HallwayPool.get(AbstractDungeon.monsterHpRng.random(act1HallwayPool.size()-1))));
            }

        }else if (AbstractDungeon.actNum == 2){//Act 2
            if (AbstractDungeon.getCurrRoom().monsters.monsters.stream().anyMatch(c -> c.type == AbstractMonster.EnemyType.BOSS)){
                theCard = (CardLibrary.getCopy(act2BossPool.get(AbstractDungeon.monsterHpRng.random(act2BossPool.size()-1))));
            }
            else if (AbstractDungeon.getCurrRoom().eliteTrigger){
                theCard = (CardLibrary.getCopy(act2ElitePool.get(AbstractDungeon.monsterHpRng.random(act2ElitePool.size()-1))));
            }else{
                theCard = (CardLibrary.getCopy(act2HallwayPool.get(AbstractDungeon.monsterHpRng.random(act2HallwayPool.size()-1))));
            }

        }else if (AbstractDungeon.actNum == 3){//Act 3
            if (AbstractDungeon.getCurrRoom().monsters.monsters.stream().anyMatch(c -> c.type == AbstractMonster.EnemyType.BOSS)){
                theCard = (CardLibrary.getCopy(act3BossPool.get(AbstractDungeon.monsterHpRng.random(act3BossPool.size()-1))));
            }
            else if (AbstractDungeon.getCurrRoom().eliteTrigger){
                theCard = (CardLibrary.getCopy(act3ElitePool.get(AbstractDungeon.monsterHpRng.random(act3ElitePool.size()-1))));
            }else{
                theCard = (CardLibrary.getCopy(act3HallwayPool.get(AbstractDungeon.monsterHpRng.random(act3HallwayPool.size()-1))));
            }

        }else {//Act 4+
            theCard = (CardLibrary.getCopy(act4PlusPool.get(AbstractDungeon.monsterHpRng.random(act4PlusPool.size()-1))));
        }

        return theCard;
    }

    public static void init() {
        collection = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        combatCollection = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    }

    public static void probe(){
        if (AbstractDungeon.actNum == 1){
            if (AbstractDungeon.floorNum >= 1){
                if (collection.group.isEmpty()) {
                    AbstractCard spookyGhost = new beginningCollectible();
                   // AbstractCard spookyGhost2 = new beginningCollectible();

                    CardModifierManager.addModifier(spookyGhost, new CollectedCardMod());
                   // CardModifierManager.addModifier(spookyGhost2, new CollectedCardMod());

                    collection.group.add(spookyGhost.makeStatEquivalentCopy());//Floor 0 collected cards.
                  //  collection.group.add(spookyGhost2.makeStatEquivalentCopy());
                }
            }
        }
    }

    public static void atBattleStart() {
        combatCollection.clear();

        for (int p = collection.group.size(); p-- > 0;) {//Behold! Count backwards to fill forwards:tm: its genius!
            AbstractCard q = collection.group.get(p).makeSameInstanceOf();
            combatCollection.addToTop(q.makeSameInstanceOf());
        }

        ArrayList<AbstractCard> toTopdeck = new ArrayList<>();
        for (AbstractCard q : combatCollection.group) {
            if (CollectorBottleField.inCollectionBottle.get(q)) {
                toTopdeck.add(q);
            }
        }
        toTopdeck.forEach(q -> {
            combatCollection.removeCard(q);
            combatCollection.addToTop(q);
        });

        DFL.atb(new ShuffleAction(DFL.pl().drawPile, false));
    }

    public static void atBattleEnd() {
        combatCollection.clear();
    }

    public static void collect(AbstractMonster m) {

        if (!collectedAlready.contains(m) && !m.id.equals(NeowBoss.ID)) {

            AbstractCard c = getCollectedCard(m);
            if (c.type == AbstractCard.CardType.SKILL && AbstractDungeon.player.hasRelic(ToxicEgg2.ID)){
                c.upgrade();
            }
            if (c.type == AbstractCard.CardType.ATTACK && AbstractDungeon.player.hasRelic(MoltenEgg2.ID)){
                c.upgrade();
            }
            if (c.type == AbstractCard.CardType.POWER && AbstractDungeon.player.hasRelic(FrozenEgg2.ID)){
                c.upgrade();
            }
            AbstractDungeon.getCurrRoom().rewards.add(new CollectibleCardReward(c));
            collectedAlready.add(m);
        }

    }

    @Deprecated
    public void overflow(){//Do not use. Replaced by better effect.
        ExcessPileRemoveEffect ePRE = new ExcessPileRemoveEffect();
        AbstractDungeon.effectList.add(ePRE);
    }

    @Deprecated
    private static int getEssenceAmount(AbstractRoom room) {
        if (room instanceof MonsterRoomBoss) {
            return 3;
        } else if (room.eliteTrigger) {
            return 2;
        }
        return 1;
    }
}

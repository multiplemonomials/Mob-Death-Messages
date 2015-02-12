package net.multiplemonomials.mobdeathmessages.reference;

public class Names
{
    public static class Data
    {
    	public final static String EEREXTENDEDPROPERTIES = "EERExtendedPlayer";
    }
    
    public static class Blocks
    {
        public static final String CHALK = "chalkBlock";
        public static final String ALCHEMICAL_CHEST = "alchemicalChest";
        public static final String ALCHEMICAL_FUEL = "alchemicalFuelBlock";
        public static final String ENERGY_COLLECTOR = "energyCollector";
        public static final String[] ENERGY_COLLECTOR_SUBTYPES = new String[]{"Antimatter", "DarkMatter", "RedMatter"};
        public static final String[] ANTIMATTER_RELAY_SUBTYPES = new String[]{"Antimatter", "DarkMatter", "RedMatter"};
        public static final String ALUDEL = "aludel";
        public static final String GLASS_BELL = "glassBell";
        public static final String CALCINATOR = "calcinator";
        public static final String RESEARCH_STATION = "researchStation";
		public static final String CONDENSER = "condenser";        
		public static final String TRANSMUTATION_TABLET = "transmutationTablet";
		public static final String ANTIMATTER_RELAY = "antiMatterRelay";
		public static final String IRRIGATED_FARMLAND = "irrigatedFarmland";

    }

    public static class Items
    {
        public static final String ALCHEMICAL_BAG = "alchemicalBag";
        public static final String ALCHEMICAL_DUST = "alchemicalDust";
        public static final String[] ALCHEMICAL_DUST_SUBTYPES = {"ash", "verdant", "azure", "minium"};
        public static final String ALCHEMICAL_FUEL = "alchemicalFuel";
        public static final String ALCHEMICAL_COAL = "alchemicalCoal";
        public static final String MOBIUS_FUEL = "mobiusFuel";
        public static final String AETERNALIS_FUEL = "aeternalisFuel";
        public static final String[] ALCHEMICAL_FUEL_SUBTYPES = {ALCHEMICAL_COAL, MOBIUS_FUEL, AETERNALIS_FUEL};
        public static final String CHALK = "chalk";
        public static final String INERT_STONE = "stoneInert";
        public static final String MINIUM_SHARD = "shardMinium";
        public static final String MINIUM_STONE = "stoneMinium";
        public static final String PHILOSOPHERS_STONE = "stonePhilosophers";
        public static final String ALCHEMICAL_UPGRADE = "alchemicalUpgrade";
        public static final String[] ALCHEMICAL_UPGRADE_SUBTYPES = {"verdant", "azure", "minium"};
        public static final String MATTER = "matter";
        public static final String[] MATTER_SUBTYPES = {"Dark", "Red"};
        public static final String MATTER_UPGRADE = "matterUpgrade";
        public static final String[] MATTER_UPGRADE_SUBTYPES = MATTER_SUBTYPES;
        public static final String DIVINING_ROD = "diviningRod";
        public static final String DARK_MATTER = "darkMatter";
        
        //Red Matter
        public static final String RED_MATTER = "redMatter";
		public static final String TALISMAN_REPAIR = "talismanRepair";
		public static final String BAND_IRON = "bandIron";
		public static final String[] IRON_BAND_SUBTYPES = {"Mundane", "Imbued"};		
		public static final String RING_FLIGHT = "ringFlight";
		//Black hole band
		public static final String RING_MAGNET = "ringMagnet";
		public static final String KLEIN_STAR = "kleinStar";
		public static final String[] KLEIN_STAR_SUBTYPES = {"Ichi", "Ni", "San", "Yon", "Go", "Zen"};
		public static final String ASSIGNMENT_GUI_ACTIVATOR = "assignmentGUIActivator";
	

    }
    
    public static class Tools
    {
    	public static final String AXE_DARK_MATTER = "axeDarkMatter";
    	public static final String HOE_DARK_MATTER = "hoeDarkMatter";
    	public static final String PICKAXE_DARK_MATTER = "pickaxeDarkMatter";
    	public static final String SHOVEL_DARK_MATTER = "shovelDarkMatter";
    	public static final String SWORD_DARK_MATTER = "swordDarkMatter";
    	public static final String FLINT_DARK_MATTER = "flintDarkMatter";
    	
    	// Red matter tools
    	public static final String AXE_RED_MATTER = "axeRedMatter";
    	public static final String HOE_RED_MATTER = "hoeRedMatter";
    	public static final String PICKAXE_RED_MATTER = "pickaxeRedMatter";
    	public static final String SHOVEL_RED_MATTER = "shovelRedMatter";
    	public static final String SWORD_RED_MATTER = "swordRedMatter";
    }

    public static class NBT
    {
        public static final String CHARGE_LEVEL = "chargeLevel";
        public static final String MODE = "mode";
        public static final String CRAFTING_GUI_OPEN = "craftingGuiOpen";
        public static final String TRANSMUTATION_GUI_OPEN = "transmutationGuiOpen";
        public static final String ALCHEMICAL_BAG_GUI_OPEN = "alchemicalBagGuiOpen";
        public static final String UUID_MOST_SIG = "UUIDMostSig";
        public static final String UUID_LEAST_SIG = "UUIDLeastSig";
        public static final String DISPLAY = "display";
        public static final String COLOR = "color";
        public static final String STATE = "teState";
        public static final String CUSTOM_NAME = "CustomName";
        public static final String DIRECTION = "teDirection";
        public static final String OWNER = "owner";
        public static final String EMC_STORED = "emcStored";
    }

    public static class Containers
    {
        public static final String VANILLA_INVENTORY = "container.inventory";
        public static final String VANILLA_CRAFTING = "container.crafting";
        public static final String ALCHEMICAL_BAG = "container.eer:" + Items.ALCHEMICAL_BAG;
        public static final String ALCHEMICAL_CHEST = "container.eer:" + Blocks.ALCHEMICAL_CHEST;
        public static final String CALCINATOR_NAME = "container.eer:" + Blocks.CALCINATOR;
        public static final String ALUDEL_NAME = "container.eer:" + Blocks.ALUDEL;

        public static final String GLASS_BELL = "container.eer:" + Blocks.GLASS_BELL;
		public static final String CONDENSER = "container.eer:" + Blocks.CONDENSER;
		public static final String TRANSMUTATION_TABLET = "container.eer:" + Blocks.TRANSMUTATION_TABLET;
		public static final String ENERGY_COLLECTOR= "container.eer:" + Blocks.ENERGY_COLLECTOR;
		public static final String ANTIMATTER_RELAY= "container.eer:" + Blocks.ANTIMATTER_RELAY;

    }

    public static class Keys
    {
        public static final String CATEGORY = "key.categories.eer";
        public static final String CHARGE = "key.charge";
        public static final String EXTRA = "key.extra";
        public static final String RELEASE = "key.release";
        public static final String TOGGLE = "key.toggle";
    }
    
    public static class GUI
    {
    	public static final String SHOW_ALL = "gui.eer.showAll";
    	public static final String SET = "gui.eer.set";
    	public static final String ITEM_INFORMATION = "gui.eer.item.information";
    	public static final String SHOW_ONLY_NO_VALUE = "gui.eer.showOnlyNoValue";
    	public static final String HAS_ENERGY_VALUE = "gui.eer.hasEnergyValue";
    	public static final String HAS_NO_ENERGY_VALUE = "gui.eer.hasNoEnergyValue";
    }
}

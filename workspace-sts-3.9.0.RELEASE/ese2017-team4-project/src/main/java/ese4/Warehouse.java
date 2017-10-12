package ese4;
import java.util.ArrayList;

/**
 * contains all the packages that later will be sent to customers
 * 
 * @author ese4
 *
 */
public class Warehouse {
	ArrayList<Package> packs;
	
	public Warehouse()
	{
		packs = new ArrayList<Package>();
	}

	
	public Package getPack(int storageIndex)
	{
		return packs.get(storageIndex);
	}
	
	public void addPack(Package pack)
	{
		packs.add(pack);
	}
	
	public void removePack(int storageIndex)
	{
		packs.remove(storageIndex);
	}
}

package sg.edu.iss.hawkerise.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.hawkerise.model.Centre;
import sg.edu.iss.hawkerise.model.Hawker;
import sg.edu.iss.hawkerise.model.MenuItem;
import sg.edu.iss.hawkerise.service.CentreInterface;
import sg.edu.iss.hawkerise.service.HawkerInterface;
import sg.edu.iss.hawkerise.service.MenuItemInterface;

@RestController
@RequestMapping(path = "/api")
public class SendToAnroidController {

	@Autowired
	CentreInterface cservice;

	@Autowired
	HawkerInterface hservice;

	@Autowired
	MenuItemInterface mservice;

	@GetMapping(path="listCentre")
	public List<Centre> getCentres(){
		
		List<Centre> resultCentres = new ArrayList<>();
		List<Centre> centreList = cservice.findAllCentres();
		
		for (Centre centre : centreList) {
			int id = centre.getId();
			String name = centre.getName();
			String address = centre.getAddress();
			String imgUrl = centre.getImgUrl();
			double latitude = centre.getLatitude();
			double longitude = centre.getLongitude();
			int numOfStalls = centre.getNumOfStalls();
			
			Centre c = new Centre(id, name, address, latitude, longitude, imgUrl,numOfStalls);
			
			resultCentres.add(c);
		}
		
		return resultCentres;
	}

	@GetMapping(path = "listHawkers/{id}")
	public List<Hawker> getHawkers(@PathVariable("id") Integer id) {

		// get integer (Centre Id) to show the list of hawker belong that Centre.
		List<Hawker> hawkerList = hservice.listHawkers(id);
		List<Hawker> resultHawkers = new ArrayList<>();

		for (Hawker hawker : hawkerList) {

			int hawkerId = hawker.getId();
			String stallName = hawker.getStallName();
			String unitNumber = hawker.getUnitNumber();
			String contactNumber = hawker.getContactNumber();
			String operatingHour = hawker.getOperatingHours();
			String closeTime = hawker.getCloseHours();
			String[] tags = hawker.getTags();
			String hawkerImg = hawker.getHawkerImg();
			String status = hawker.getStatus();

			Hawker h = new Hawker(hawkerId, stallName, unitNumber, contactNumber, tags, operatingHour, closeTime,
					hawkerImg, status);

			resultHawkers.add(h);
		}

		return resultHawkers;
	}

	@GetMapping(path = "listMenuItem/{id}")
	public List<MenuItem> getMenuItems(@PathVariable("id") Integer id) {

		List<MenuItem> menuItemList = mservice.listMenuItems(id);
		List<MenuItem> resultMenuItems = new ArrayList<>();

		for (MenuItem menuItem : menuItemList) {

			int menuItemId = menuItem.getId();
			String menuItemName = menuItem.getName();
			String menuItemDesc = menuItem.getDescription();
			double menuItemPrice = menuItem.getPrice();
			String menuItemStatus = menuItem.getStatus();
			String menuItemPhoto = menuItem.getLocalUrl();

			MenuItem m = new MenuItem(menuItemId, menuItemName, menuItemDesc, menuItemPrice, menuItemStatus,
					menuItemPhoto);

			resultMenuItems.add(m);
		}

		return resultMenuItems;
	}

	@RequestMapping(path = "nearestCentre/{lat}/{lon}/{distance}")
	public List<Centre> getNearestCentres(@PathVariable("lat") Double lat, @PathVariable("lon") Double lon,
			@PathVariable("distance") Integer dist) {
		List<Centre> centreList = cservice.findAllCentres();
		List<Centre> nearestCentre = new ArrayList<>();
		for (Centre c : centreList) {
			if (calcDist(lat, lon, c.getLatitude(), c.getLongitude()) < dist) {
				int id = c.getId();
				String name = c.getName();
				String address = c.getAddress();
				String imgUrl = c.getImgUrl();
				double latitude = c.getLatitude();
				double longitude = c.getLongitude();
				double distance = calcDist(lat, lon, c.getLatitude(), c.getLongitude());

				Centre newc = new Centre(id, name, address, latitude, longitude, distance, imgUrl);

				nearestCentre.add(newc);
			}

		}
		nearestCentre.stream().sorted(Comparator.comparing(Centre::getDistance));

		return nearestCentre;
	}

	public static float calcDist(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist / 1000;
	}
}

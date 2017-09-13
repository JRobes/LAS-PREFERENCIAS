 
package zzz.preferences;

import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.preference.PreferenceStore;

public class MyPreferences {
	@Execute
	public void execute() {
		 PreferenceManager mgr = new PreferenceManager();
		// PreferenceNode one = new PreferenceNode("one", "One",
		//		 ImageDescriptor.createFromFile(MyPreferences.class, "/icons/sample.png"),
		//         PrefPageOne.class.getName());
		 System.out.println("APP intance location:\t"+Platform.getInstanceLocation().getURL().toString());
		 System.out.println("APP intance location:\t"+Platform.getInstanceLocation().getURL().getPath().toString());

		 PreferenceStore ps = new PreferenceStore("showprefs.properties");
		  // Set the preference store
		 
		 try {
		      System.out.println("a          we     ");
		      ps.load();

	
		  } catch (IOException e) {
		      System.out.println("cargando las prefereneciaasss con IOException");
		   }

		 PreferenceNode one = new PreferenceNode("one", new PrefPageOne());

		 PreferenceNode two = new PreferenceNode("two", new PrefPageTwo());
		    // Add the nodes
		 mgr.addToRoot(one);
		 mgr.addTo(one.getId(), two);
		 // Create the preferences dialog
		 PreferenceDialog dlg = new PreferenceDialog(null, mgr);


		   dlg.setPreferenceStore(ps);
		      String[] names = ps.preferenceNames();
		      
		      for(int i = 0; i < names.length;i++) {
		    	 System.out.println("names["+i+"]\t"+names[i]+" = "+ps.getBoolean(names[i]));
		      }
		    // Open the dialog
		   dlg.open();
	}
		
}
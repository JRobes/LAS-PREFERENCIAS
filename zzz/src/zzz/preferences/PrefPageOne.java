package zzz.preferences;

import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * This class creates a preference page
 */
class PrefPageOne extends PreferencePage {
  // Names for preferences
  private static final String ONE = "one.one";
  private static final String TWO = "one.two";
  private static final String THREE = "one.three";
  public Preferences defaultPrefs, defaultPrefs2, instancePrefs;

  // Text fields for user to enter preferences
  private Text fieldOne;
  private Text fieldTwo;
  private Text fieldThree;

  public PrefPageOne() {
	    super("One");
	    setDescription("RRRRRRRRRR");
	    
	    defaultPrefs = DefaultScope.INSTANCE.getNode("nastraneditor.preferences.general");
	    //defaultPrefs2 = DefaultScope.INSTANCE.getNode("nastraneditor.preferences.general");
	    instancePrefs = InstanceScope.INSTANCE.getNode("nastraneditor.preferences.general");
/*
	    try {
			String childrenNames[] = defaultPrefs2.childrenNames();
			System.out.println("numero de children......"+childrenNames.length);
			for(int i = 0; i< childrenNames.length; i++) {
				System.out.println("Children......"+childrenNames[i]);
			}
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    */
	    //String text = defaultPrefs.get("texto2", defaultPrefs.get("texto2", "Aqu� no puede llegar"));
	    //System.out.println(text);
	  }
  
  /**
   * Creates the controls for this page
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(2, false));

    // Get the preference store
    IPreferenceStore preferenceStore = getPreferenceStore();

    // Create three text fields.
    // Set the text in each from the preference store
    new Label(composite, SWT.LEFT).setText("Field One:");
    fieldOne = new Text(composite, SWT.BORDER);
    fieldOne.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    fieldOne.setText(instancePrefs.get("texto1", defaultPrefs.get("texto1", "NUNCA DEBE LLEGAR AQUI..")));

   // fieldOne.setText(instancePrefs.get("texto2", defaultPrefs.get("texto2", "Aqu� no puede llegar")));
    		//preferenceStore.getString(ONE));

    new Label(composite, SWT.LEFT).setText("Field Two:");
    fieldTwo = new Text(composite, SWT.BORDER);
    fieldTwo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    fieldTwo.setText(instancePrefs.get("texto2", defaultPrefs.get("texto2", "NUNCA DEBE LLEGAR AQUI..")));

    new Label(composite, SWT.LEFT).setText("Field Three:");
    fieldThree = new Text(composite, SWT.BORDER);
    fieldThree.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    fieldThree.setText(instancePrefs.get("texto3", defaultPrefs.get("texto3", "NUNCA DEBE LLEGAR AQUI..")));

    return composite;
  }

  /**
   * Called when user clicks Restore Defaults
   */
  protected void performDefaults() {
    // Get the preference store
    IPreferenceStore preferenceStore = getPreferenceStore();

    // Reset the fields to the defaults
    fieldOne.setText(defaultPrefs.get("texto1", "NUNCA DEBE LLEGAR AQUI.."));
    fieldTwo.setText(defaultPrefs.get("texto2", "NUNCA DEBE LLEGAR AQUI.."));
    fieldThree.setText(defaultPrefs.get("texto3", "NUNCA DEBE LLEGAR AQUI.."));
  }

  /**
   * Called when user clicks Apply or OK
   * 
   * @return boolean
   */
  public boolean performOk() {
    // Get the preference store
    IPreferenceStore preferenceStore = getPreferenceStore();

    // Set the values from the fields
    if (fieldOne != null) instancePrefs.put("texto1",fieldOne.getText());//preferenceStore.setValue(ONE, fieldOne.getText());
    if (fieldTwo != null) instancePrefs.put("texto2",fieldTwo.getText());
    if (fieldThree != null) instancePrefs.put("texto3",fieldThree.getText());
       
     try {
		instancePrefs.flush();
	} catch (BackingStoreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    // Return true to allow dialog to close
    return true;
  }
}
/*
 * Autopsy Forensic Browser
 *
 * Copyright 2012 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.casemodule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.CancellationException;
import java.util.logging.Level;
import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;
import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataSourceProcessor;
import org.sleuthkit.autopsy.coreutils.LocalDisk;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.autopsy.coreutils.MessageNotifyUtil;
import org.sleuthkit.autopsy.coreutils.PlatformUtil;

/**
 * ImageTypePanel for adding a local disk or partition such as PhysicalDrive0 or
 * C:.
 */
final class LocalDiskPanel extends JPanel {

    private static final Logger logger = Logger.getLogger(LocalDiskPanel.class.getName());
    private static LocalDiskPanel instance;
    private static final long serialVersionUID = 1L;

    private PropertyChangeSupport pcs = null;

    private List<LocalDisk> disks;
    private LocalDiskModel model;
    private boolean enableNext = false;

    /**
     * Creates new form LocalDiskPanel
     */
    public LocalDiskPanel() {
        this.disks = new ArrayList<>();
        initComponents();
        customInit();

        createTimeZoneList();

    }

    /**
     * Get the default instance of this panel.
     */
    public static synchronized LocalDiskPanel getDefault() {
        if (instance == null) {
            instance = new LocalDiskPanel();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    private void customInit() {
        model = new LocalDiskModel();
        diskComboBox.setModel(model);
        diskComboBox.setRenderer(model);

        errorLabel.setVisible(false);
        errorLabel.setText("");
        diskComboBox.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diskLabel = new javax.swing.JLabel();
        diskComboBox = new javax.swing.JComboBox<>();
        errorLabel = new javax.swing.JLabel();
        timeZoneLabel = new javax.swing.JLabel();
        timeZoneComboBox = new javax.swing.JComboBox<>();
        noFatOrphansCheckbox = new javax.swing.JCheckBox();
        descLabel = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(0, 65));
        setPreferredSize(new java.awt.Dimension(485, 65));

        diskLabel.setFont(diskLabel.getFont().deriveFont(diskLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(diskLabel, org.openide.util.NbBundle.getMessage(LocalDiskPanel.class, "LocalDiskPanel.diskLabel.text")); // NOI18N

        diskComboBox.setFont(diskComboBox.getFont().deriveFont(diskComboBox.getFont().getStyle() & ~java.awt.Font.BOLD, 11));

        errorLabel.setFont(errorLabel.getFont().deriveFont(errorLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(errorLabel, org.openide.util.NbBundle.getMessage(LocalDiskPanel.class, "LocalDiskPanel.errorLabel.text")); // NOI18N

        timeZoneLabel.setFont(timeZoneLabel.getFont().deriveFont(timeZoneLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(timeZoneLabel, org.openide.util.NbBundle.getMessage(LocalDiskPanel.class, "LocalDiskPanel.timeZoneLabel.text")); // NOI18N

        timeZoneComboBox.setFont(timeZoneComboBox.getFont().deriveFont(timeZoneComboBox.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        timeZoneComboBox.setMaximumRowCount(30);

        noFatOrphansCheckbox.setFont(noFatOrphansCheckbox.getFont().deriveFont(noFatOrphansCheckbox.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(noFatOrphansCheckbox, org.openide.util.NbBundle.getMessage(LocalDiskPanel.class, "LocalDiskPanel.noFatOrphansCheckbox.text")); // NOI18N
        noFatOrphansCheckbox.setToolTipText(org.openide.util.NbBundle.getMessage(LocalDiskPanel.class, "LocalDiskPanel.noFatOrphansCheckbox.toolTipText")); // NOI18N

        descLabel.setFont(descLabel.getFont().deriveFont(descLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(descLabel, org.openide.util.NbBundle.getMessage(LocalDiskPanel.class, "LocalDiskPanel.descLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(diskLabel)
                    .addComponent(diskComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeZoneLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timeZoneComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(noFatOrphansCheckbox)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(descLabel)))
                .addGap(0, 102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(diskLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diskComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeZoneLabel)
                    .addComponent(timeZoneComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(noFatOrphansCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descLabel;
    private javax.swing.JComboBox<LocalDisk> diskComboBox;
    private javax.swing.JLabel diskLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JCheckBox noFatOrphansCheckbox;
    private javax.swing.JComboBox<String> timeZoneComboBox;
    private javax.swing.JLabel timeZoneLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Return the currently selected disk path.
     *
     * @return String selected disk path
     */
    //@Override
    public String getContentPaths() {
        if (disks.size() > 0) {
            LocalDisk selected = (LocalDisk) diskComboBox.getSelectedItem();
            return selected.getPath();
        } else {
            return "";
        }
    }

    /**
     * Set the selected disk.
     */
    // @Override
    public void setContentPath(String s) {
        for (int i = 0; i < disks.size(); i++) {
            if (disks.get(i).getPath().equals(s)) {
                diskComboBox.setSelectedIndex(i);
            }
        }
    }

    public String getTimeZone() {
        String tz = timeZoneComboBox.getSelectedItem().toString();
        return tz.substring(tz.indexOf(")") + 2).trim();

    }

    boolean getNoFatOrphans() {
        return noFatOrphansCheckbox.isSelected();
    }

    /**
     * Should we enable the wizard's next button? Always return true because we
     * control the possible selections.
     *
     * @return true
     */
    //@Override
    public boolean validatePanel() {
        return enableNext;
    }

    //@Override
    public void reset() {
        //nothing to reset

    }

    /**
     * Set the focus to the diskComboBox and refreshes the list of disks.
     */
    // @Override
    public void select() {
        diskComboBox.requestFocusInWindow();
        model.loadDisks();

    }

    /**
     * Creates the drop down list for the time zones and then makes the local
     * machine time zone to be selected.
     */
    public void createTimeZoneList() {
        // load and add all timezone
        String[] ids = SimpleTimeZone.getAvailableIDs();
        for (String id : ids) {
            TimeZone zone = TimeZone.getTimeZone(id);
            int offset = zone.getRawOffset() / 1000;
            int hour = offset / 3600;
            int minutes = (offset % 3600) / 60;
            String item = String.format("(GMT%+d:%02d) %s", hour, minutes, id); //NON-NLS

            /*
             * DateFormat dfm = new SimpleDateFormat("z");
             * dfm.setTimeZone(zone); boolean hasDaylight =
             * zone.useDaylightTime(); String first = dfm.format(new Date(2010,
             * 1, 1)); String second = dfm.format(new Date(2011, 6, 6)); int mid
             * = hour * -1; String result = first + Integer.toString(mid);
             * if(hasDaylight){ result = result + second; }
             * timeZoneComboBox.addItem(item + " (" + result + ")");
             */
            timeZoneComboBox.addItem(item);
        }
        // get the current timezone
        TimeZone thisTimeZone = Calendar.getInstance().getTimeZone();
        int thisOffset = thisTimeZone.getRawOffset() / 1000;
        int thisHour = thisOffset / 3600;
        int thisMinutes = (thisOffset % 3600) / 60;
        String formatted = String.format("(GMT%+d:%02d) %s", thisHour, thisMinutes, thisTimeZone.getID()); //NON-NLS

        // set the selected timezone
        timeZoneComboBox.setSelectedItem(formatted);
    }

    @SuppressWarnings("rawtypes")
    private class LocalDiskModel implements ComboBoxModel, ListCellRenderer {

        private Object selected;

        private boolean ready = false;

        private volatile boolean loadingDisks = false;

        List<LocalDisk> physicalDrives = new ArrayList<>();
        List<LocalDisk> partitions = new ArrayList<>();

        //private String SELECT = "Select a local disk:";
        private String LOADING = NbBundle.getMessage(this.getClass(), "LocalDiskPanel.localDiskModel.loading.msg");
        private String NO_DRIVES = NbBundle.getMessage(this.getClass(), "LocalDiskPanel.localDiskModel.nodrives.msg");

        LocalDiskThread worker = null;

        private void loadDisks() {

            // if there is a worker already building the lists, then cancel it first.
            if (loadingDisks && worker != null) {
                worker.cancel(false);
            }

            // Clear the lists
            errorLabel.setText("");
            disks = new ArrayList<>();
            physicalDrives = new ArrayList<>();
            partitions = new ArrayList<>();
            diskComboBox.setEnabled(false);
            ready = false;
            enableNext = false;
            loadingDisks = true;

            worker = new LocalDiskThread();
            worker.execute();

        }

        @Override
        public void setSelectedItem(Object anItem) {
            if (ready) {
                selected = (LocalDisk) anItem;
                enableNext = true;

                try {
                    firePropertyChange(DataSourceProcessor.DSP_PANEL_EVENT.UPDATE_UI.toString(), false, true);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "LocalDiskPanel listener threw exception", e); //NON-NLS
                    MessageNotifyUtil.Notify.show(NbBundle.getMessage(this.getClass(), "LocalDiskPanel.moduleErr"),
                            NbBundle.getMessage(this.getClass(), "LocalDiskPanel.moduleErr.msg"),
                            MessageNotifyUtil.MessageType.ERROR);
                }
            }
        }

        @Override
        public Object getSelectedItem() {
            if (ready) {
                if (disks.isEmpty()) {
                    return NO_DRIVES;
                }
                return selected;
            } else {
                return LOADING;
            }
        }

        @Override
        public int getSize() {
            if (ready) {
                if (disks.isEmpty()) {
                    return 1;
                }
                return disks.size();
            } else {
                return 1;
            }
        }

        @Override
        public Object getElementAt(int index) {
            if (ready) {
                if (disks.isEmpty()) {
                    return NO_DRIVES;
                }
                return disks.get(index);
            } else {
                return LOADING;
            }
        }

        @Override
        public void addListDataListener(ListDataListener l) {
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel();
            if ((index == physicalDrives.size() - 1) && (physicalDrives.size() > 0)) {
                panel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
            }

            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            } else {
                label.setBackground(list.getBackground());
                label.setForeground(list.getForeground());
            }

            if (value != null) {
                String localDiskString = value.toString();
                label.setText(value.toString());
                if ((localDiskString.equals(LOADING)) || (localDiskString.equals(NO_DRIVES))) {
                    label.setFont(label.getFont().deriveFont(Font.ITALIC));
                    label.setBackground(Color.GRAY);
                }
            }
            label.setOpaque(true);
            label.setBorder(new EmptyBorder(2, 2, 2, 2));

            panel.add(label, BorderLayout.CENTER);
            return panel;
        }

        class LocalDiskThread extends SwingWorker<Object, Void> {

            private Logger logger = Logger.getLogger(LocalDiskThread.class.getName());

            @Override
            protected Object doInBackground() throws Exception {
                // Populate the lists
                physicalDrives = PlatformUtil.getPhysicalDrives();
                partitions = PlatformUtil.getPartitions();

                return null;
            }

            private void displayErrors() {
                if (physicalDrives.isEmpty() && partitions.isEmpty()) {
                    if (PlatformUtil.isWindowsOS()) {
                        errorLabel.setText(
                                NbBundle.getMessage(this.getClass(), "LocalDiskPanel.errLabel.disksNotDetected.text"));
                        errorLabel.setToolTipText(NbBundle.getMessage(this.getClass(),
                                "LocalDiskPanel.errLabel.disksNotDetected.toolTipText"));
                    } else {
                        errorLabel.setText(
                                NbBundle.getMessage(this.getClass(), "LocalDiskPanel.errLabel.drivesNotDetected.text"));
                        errorLabel.setToolTipText(NbBundle.getMessage(this.getClass(),
                                "LocalDiskPanel.errLabel.drivesNotDetected.toolTipText"));
                    }
                    diskComboBox.setEnabled(false);
                } else if (physicalDrives.isEmpty()) {
                    errorLabel.setText(
                            NbBundle.getMessage(this.getClass(), "LocalDiskPanel.errLabel.someDisksNotDetected.text"));
                    errorLabel.setToolTipText(NbBundle.getMessage(this.getClass(),
                            "LocalDiskPanel.errLabel.someDisksNotDetected.toolTipText"));
                }
            }

            @Override
            protected void done() {
                try {
                    super.get(); //block and get all exceptions thrown while doInBackground()
                } catch (CancellationException ex) {
                    logger.log(Level.INFO, "Loading local disks was canceled, which should not be possible."); //NON-NLS
                } catch (InterruptedException ex) {
                    logger.log(Level.INFO, "Loading local disks was interrupted."); //NON-NLS
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Fatal error when loading local disks", ex); //NON-NLS
                } finally {
                    if (!this.isCancelled()) {
                        enableNext = false;
                        displayErrors();

                        worker = null;
                        loadingDisks = false;

                        disks.addAll(physicalDrives);
                        disks.addAll(partitions);

                        if (disks.size() > 0) {
                            diskComboBox.setEnabled(true);
                            diskComboBox.setSelectedIndex(0);
                        }
                        ready = true;
                    } else {
                        logger.log(Level.INFO, "Loading local disks was canceled, which should not be possible."); //NON-NLS
                    }
                }
            }
        }
    }
}

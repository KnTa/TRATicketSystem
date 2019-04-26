package ui;

import station.Station;
import user.ReserveCondition;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;


public class TicketUI {
    public static final int MAX_SEAT=52;

    private JPanel pnlBoard;
    private JComboBox comboBox1;
    private JPanel pnlSeat;
    private JPanel pnlCar;
    private JPanel pnlControl;
    private JPanel pnlReservation;
    private JPanel pnlSearch;
    private JComboBox cmb_reserve_departure;
    private JComboBox cmb_reserve_arrive;
    private JComboBox cmb_search_departure;
    private JComboBox cmb_search_arrive;
    private JTextField txf_reserve_year;
    private JTextField txf_reserve_day;
    private JTextField txf_reserve_month;
    private JComboBox cmb_reserve_start_hour;
    private JComboBox cmb_reserve_start_minute;
    private JComboBox cmb_reserve_end_hour;
    private JComboBox cmb_reserve_end_minute;
    private JTextField txf_search_year;
    private JTextField txf_search_month;
    private JTextField txf_search_day;
    private JComboBox cmb_search_start_hour;
    private JComboBox cmb_search_start_minute;
    private JComboBox cmb_search_end_hour;
    private JComboBox cmb_search_end_minute;
    private JButton btn_search;
    private JComboBox cmb_search_condition;
    private JComboBox cmb_reserve_condition;
    private JButton btn_reserve;
    private JPanel pnl_result;
    private JLabel lab_start_station;
    private JLabel lab_end_station;
    private JLabel lab_ticket_id;
    private JLabel lab_start_station_time;
    private JLabel lab_end_station_time;
    private JLabel lab_ticket_status;
    private JButton btn_confirm;
    private Map<Integer, JButton> btnSeat;

    public TicketUI(){
        btnSeat = new TreeMap<>();
        initSeatPanelUI();
        initComboBox();
        initReserveButton();
        initSearchButton();
        initConfirmButton();
    }
    //設定GridBagConstraints
    private GridBagConstraints getGridBagConstraintsSetting(int x,int y){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 15;
        gbc.ipadx = 15;
        gbc.gridx=x;
        gbc.gridy=y;
        return gbc;
    }
    //設定combobox內容為小時
    private void setComboBoxHour(JComboBox comboBox){
        for(int i=0;i<24;i++){
            comboBox.addItem(Integer.toString(i));
        }
    }
    //設定combobox內容為分鐘
    private void setComboBoxMinute(JComboBox comboBox){
        for(int i=0;i<60;i++){
            comboBox.addItem(Integer.toString(i));
        }
    }
    //設定combobox內容為車站
    private void setStationComboBox(JComboBox comboBox){
        Iterator it = User.getAllStation().iterator();
        while(it.hasNext()){
            comboBox.addItem(it.next());
        }
    }
    //設定combobox內容為選座條件
    class Condition{
        private int key;
        private String name;

        Condition(int key, String name){
            this.key=key;
            this.name=name;
        }

        public int getKey() {
            return key;
        }
        public String getName(){
            return name;
        }
        public String toString(){
            return name;
        }
    }
    private void setConditionComboBox(JComboBox comboBox){
        comboBox.addItem(new Condition(ReserveCondition.SEAT_CONDITION_NONE, "無"));
        comboBox.addItem(new Condition(ReserveCondition.SEAT_CONDITION_WINDOW, "靠窗"));
        comboBox.addItem(new Condition(ReserveCondition.SEAT_CONDITION_AISLE, "靠走道"));
    }
    //在Panel新增座位按鈕
    private void addSeatButton(JPanel panel,JButton button,int x,int y,int seatNum){
        panel.add(button, getGridBagConstraintsSetting(x,y));
        btnSeat.put(new Integer(seatNum),button);
        button.setEnabled(false);
        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
        });
    }
    //在Panel新增component
    private void addComponent(JPanel panel, Component component, int x, int y){
        panel.add(component, getGridBagConstraintsSetting(x,y));
    }
    //初始化座位UI
    private void initSeatPanelUI(){
        pnlSeat.setLayout(new GridBagLayout());

        for(int i=0;i<13;i++){
            int seat=i*4+1;
            addSeatButton(pnlSeat, new JButton(Integer.toString(seat)), 0, i, seat);
            seat=i*4+3;
            addSeatButton(pnlSeat, new JButton(Integer.toString(seat)), 1, i, seat);
            addComponent(pnlSeat, new JPanel(), 2, i);
            seat=i*4+4;
            addSeatButton(pnlSeat, new JButton(Integer.toString(seat)), 3, i, seat);
            seat=i*4+2;
            addSeatButton(pnlSeat, new JButton(Integer.toString(seat)), 4, i, seat);
        }
    }
    //初始化combobox
    private void initComboBox(){
        setComboBoxHour(cmb_search_start_hour);
        setComboBoxHour(cmb_search_end_hour);
        setComboBoxHour(cmb_reserve_start_hour);
        setComboBoxHour(cmb_reserve_end_hour);
        setComboBoxMinute(cmb_search_start_minute);
        setComboBoxMinute(cmb_search_end_minute);
        setComboBoxMinute(cmb_reserve_start_minute);
        setComboBoxMinute(cmb_reserve_end_minute);

        setStationComboBox(cmb_reserve_departure);
        setStationComboBox(cmb_reserve_arrive);
        setStationComboBox(cmb_search_departure);
        setStationComboBox(cmb_search_arrive);

        setConditionComboBox(cmb_reserve_condition);
        setConditionComboBox(cmb_search_condition);
    }
    //初始化定位按鈕
    private void initReserveButton(){
        btn_reserve.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
    }
    //初始化搜尋按鈕
    private void initSearchButton(){
        btn_search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
    }
    //初始化確定按鈕
    private void initConfirmButton(){
        btn_confirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
        btn_confirm.setEnabled(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TicketUI");
        frame.setSize(500, 500);
        frame.setContentPane(new TicketUI().pnlBoard);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

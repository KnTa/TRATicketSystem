package ui;

import reservation.Ticket;
import station.Station;
import user.ReserveCondition;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.*;


public class TicketUI {
    public static final int MAX_SEAT = 52;

    private static JFrame frame;

    private JPanel pnlBoard;
    private JComboBox cmb_car;
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
    private JLabel lab_train_time;
    private JButton btn_confirm;
    private Map<Integer, JButton> btnSeat;
    private User user;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat simpleDateFormat_time = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    //Data
    Map<Integer, Ticket> ticketMap;
    Map<Integer, Map<Integer, Boolean>> allSeatStatus;

    public TicketUI() {
        btnSeat = new TreeMap<>();
        initSeatPanelUI();
        initComboBox();
        initReserveButton();
        initSearchButton();
        initConfirmButton();
        user = new User();
    }

    //設定GridBagConstraints
    private GridBagConstraints getGridBagConstraintsSetting(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 15;
        gbc.ipadx = 15;
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }

    //設定combobox內容為小時
    private void setComboBoxHour(JComboBox comboBox) {
        for (int i = 0; i < 24; i++) {
            comboBox.addItem(Integer.toString(i));
        }
    }

    //設定combobox內容為分鐘
    private void setComboBoxMinute(JComboBox comboBox) {
        for (int i = 0; i < 60; i++) {
            comboBox.addItem(Integer.toString(i));
        }
    }

    //設定combobox內容為車站
    private void setStationComboBox(JComboBox comboBox) {
        Iterator it = User.getAllStation().iterator();
        while (it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    //設定combobox內容為選座條件
    class Condition {
        private int key;
        private String name;

        Condition(int key, String name) {
            this.key = key;
            this.name = name;
        }

        public int getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return name;
        }
    }

    private void setConditionComboBox(JComboBox comboBox) {
        comboBox.addItem(new Condition(ReserveCondition.SEAT_CONDITION_NONE, "無"));
        comboBox.addItem(new Condition(ReserveCondition.SEAT_CONDITION_WINDOW, "靠窗"));
        comboBox.addItem(new Condition(ReserveCondition.SEAT_CONDITION_AISLE, "靠走道"));
    }

    //在Panel新增座位按鈕
    private void addSeatButton(JPanel panel, JButton button, int x, int y, int seatNum) {
        panel.add(button, getGridBagConstraintsSetting(x, y));
        btnSeat.put(new Integer(seatNum), button);
        button.setEnabled(false);
        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (user != null) {
                            //!!!!entry為暫時的!!!!//需要正式的ticket id 選擇
                            Map.Entry<Integer, Ticket> entry = ticketMap.entrySet().iterator().next();
                            try {
                                Integer car = (Integer) cmb_car.getSelectedItem();
                                user.selectSeat(entry.getValue().getStatus().getID()
                                        , car, Integer.parseInt(button.getText()));
                                updateAllSeatStatus(entry);
                                setSeatButton(car);
                            } catch (Exception error) {
                                standErrorBox(error);
                            }
                        }
                    }
                });
    }

    //在Panel新增component
    private void addComponent(JPanel panel, Component component, int x, int y) {
        panel.add(component, getGridBagConstraintsSetting(x, y));
    }

    //初始化座位UI
    private void initSeatPanelUI() {
        pnlSeat.setLayout(new GridBagLayout());

        for (int i = 0; i < 13; i++) {
            int seat = i * 4 + 1;
            addSeatButton(pnlSeat, new JButton(Integer.toString(seat)), 0, i, seat);
            seat = i * 4 + 3;
            addSeatButton(pnlSeat, new JButton(Integer.toString(seat)), 1, i, seat);
            addComponent(pnlSeat, new JPanel(), 2, i);
            seat = i * 4 + 4;
            addSeatButton(pnlSeat, new JButton(Integer.toString(seat)), 3, i, seat);
            seat = i * 4 + 2;
            addSeatButton(pnlSeat, new JButton(Integer.toString(seat)), 4, i, seat);
        }
    }

    //初始化combobox
    private void initComboBox() {
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

        cmb_car.setEnabled(false);
    }

    //初始化定位按鈕
    private void initReserveButton() {
        btn_reserve.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            reserveProgress();
                            btn_reserve.setEnabled(false);
                            btn_confirm.setEnabled(true);
                        } catch (Exception error) {
                            standErrorBox(error);
                        }
                    }
                }
        );
    }

    private void reserveProgress() throws Exception {
        user = new User();
        int year, month, day;
        Date startDate;
        Date endDate;
        Integer start_hour = Integer.parseInt((String) cmb_reserve_start_hour.getSelectedItem());
        Integer start_minute = Integer.parseInt((String) cmb_reserve_start_minute.getSelectedItem());

        Integer end_hour = Integer.parseInt((String) cmb_reserve_end_hour.getSelectedItem());
        Integer end_minute = Integer.parseInt((String) cmb_reserve_end_minute.getSelectedItem());

        year = Integer.parseInt(txf_reserve_year.getText());
        month = Integer.parseInt(txf_reserve_month.getText());
        day = Integer.parseInt(txf_reserve_day.getText());
        startDate = new GregorianCalendar(year, month - 1, day, start_hour, start_minute).getTime();
        endDate = new GregorianCalendar(year, month - 1, day, end_hour, end_minute).getTime();

        Station departure = (Station) cmb_reserve_departure.getSelectedItem();
        Station arrive = (Station) cmb_reserve_arrive.getSelectedItem();
        if (departure.compare(arrive)) {
            throw new Exception("相同起訖站");
        }
        Condition reserveSeatCondition = (Condition) cmb_reserve_condition.getSelectedItem();

        ticketMap = user.reserveTicket(departure.getID(), arrive.getID(),
                startDate, endDate, 1, reserveSeatCondition.getKey(), 1);

        setReservationComponentEnable(false);

        Map.Entry<Integer, Ticket> entry = ticketMap.entrySet().iterator().next();
        setTicketInfo(entry);

        setSeatButtonStatus(entry);
    }

    private void updateAllSeatStatus(Map.Entry<Integer, Ticket> entry)throws Exception{
        allSeatStatus = user.getAllSeatCurrentStatus(entry.getValue().getTicketInfo().getTrain_seat_info().getTrain_id()
                , entry.getValue().getTicketInfo().getTrain_seat_info().getDate()
                , entry.getValue().getTicketInfo().getDeparture().getID(), entry.getValue().getTicketInfo().getArrive().getID());
    }
    private void setSeatButtonStatus(Map.Entry<Integer, Ticket> entry)throws Exception {

        updateAllSeatStatus(entry);

        allSeatStatus.forEach((car_id, seats) -> cmb_car.addItem(car_id));
        cmb_car.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Integer car = (Integer) cmb_car.getSelectedItem();
                    setSeatButtonByCar(car);
                    ticketMap.forEach((id, ticket) -> setReserveSeatButton(car, ticket));
                } catch (Exception error) {
                    standErrorBox(error);
                }
            }
        });
        Integer car = entry.getValue().getTicketInfo().getTrain_seat_info().getSeat_info().getCar_id();
        cmb_car.setSelectedItem(car);
        cmb_car.setEnabled(true);

        setSeatButton(car);
    }

    public void setSeatButton(int car)throws Exception{
        //設定座位狀態
        setSeatButtonByCar(car);
        //設置自己訂的座位
        ticketMap.forEach((id, ticket) -> setReserveSeatButton(car, ticket));
    }

    private void setSeatButtonByCar(int car) {
        Map<Integer, Boolean> seatStatus = allSeatStatus.get(car);
        for (int i = 1; i <= MAX_SEAT; i++) {
            if (seatStatus.get(i) == null) {
                break;
            }
            JButton seat = btnSeat.get(i);
            if (!seatStatus.get(i)) {
                seat.setEnabled(false);
                seat.setBackground(Color.RED);
            } else {
                seat.setEnabled(true);
                seat.setBackground(UIManager.getColor ( "Panel.background" ));
            }
        }
    }

    private void setReserveSeatButton(int car, Ticket ticket) {
        if (ticket.getTicketInfo().getTrain_seat_info().getSeat_info().getCar_id()
                == car) {
            JButton seatButton = btnSeat.get(ticket.getTicketInfo().getTrain_seat_info().getSeat_info().getSeat_id());
            seatButton.setEnabled(false);
            seatButton.setBackground(Color.GREEN);
        }
    }

    private void setTicketInfo(Map.Entry<Integer, Ticket> entry) {
        lab_start_station.setText(entry.getValue().getTicketInfo().getDeparture().getName_TCN());
        lab_end_station.setText(entry.getValue().getTicketInfo().getArrive().getName_TCN());
        lab_ticket_id.setText(Integer.toString(entry.getValue().getStatus().getID()));
        Date departureStation = user.getStationDepartureTime(entry.getValue().getTicketInfo().getTrain_seat_info().getTrain_id()
                , entry.getValue().getTicketInfo().getTrain_seat_info().getDate()
                , entry.getValue().getTicketInfo().getDeparture().getID());
        Date arriveStation = user.getStationArriveTime(entry.getValue().getTicketInfo().getTrain_seat_info().getTrain_id()
                , entry.getValue().getTicketInfo().getTrain_seat_info().getDate()
                , entry.getValue().getTicketInfo().getArrive().getID());
        lab_start_station_time.setText(simpleDateFormat_time.format(departureStation));
        lab_end_station_time.setText(simpleDateFormat_time.format(arriveStation));
        lab_ticket_status.setText(entry.getValue().getStatus().getStatusString());
        lab_train_time.setText(Integer.toString(entry.getValue().getTicketInfo().getTrain_seat_info().getTrain_id()));
    }

    public void standErrorBox(Exception e) {
        JOptionPane.showMessageDialog(frame, e.getClass().getName() + ": " + e.getMessage());
    }

    //初始化搜尋按鈕
    private void initSearchButton() {
        btn_search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
    }

    //初始化確定按鈕
    private void initConfirmButton() {
        btn_confirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (user != null) {
                            try {
                                user.confirm();
                                btn_confirm.setEnabled(false);
                                Map.Entry<Integer, Ticket> entry = ticketMap.entrySet().iterator().next();
                                lab_ticket_status.setText(entry.getValue().getStatus().getStatusString());
                            }catch (Exception error){standErrorBox(error);}

                        }
                    }
                }
        );
        btn_confirm.setEnabled(false);
    }

    //設定訂票元件是否enable
    private void setReservationComponentEnable(boolean enable) {
        txf_reserve_day.setEnabled(enable);
        txf_reserve_month.setEnabled(enable);
        txf_reserve_year.setEnabled(enable);
        cmb_reserve_arrive.setEnabled(enable);
        cmb_reserve_departure.setEnabled(enable);
        cmb_reserve_start_minute.setEnabled(enable);
        cmb_reserve_start_hour.setEnabled(enable);
        cmb_reserve_end_minute.setEnabled(enable);
        cmb_reserve_end_hour.setEnabled(enable);
        cmb_reserve_condition.setEnabled(enable);
    }

    public static void main(String[] args) {
        frame = new JFrame("TicketUI");
        frame.setSize(500, 500);
        frame.setContentPane(new TicketUI().pnlBoard);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

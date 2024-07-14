package WeChat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Chat_Info extends Box{
    JButton avatar;
    JTextPane textPane;
    Font font;
    public Chat_Info(int show_pane_width, BufferedImage bufferedImage, String who, String chat_text, Font font) {
        super(BoxLayout.X_AXIS);
        this.font = font;
        avatar = new JButton();
        avatar.setBorderPainted(false);
        avatar.setBorder(null);
        avatar.setContentAreaFilled(false);
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(this.font);
        textPane.setBackground(Color.GREEN);
        skjjddddddddddddddddd 
        dhjkkkkkkkkkkslj
        多看看坎坎坷坷坎坎坷坷卡卡卡
        大舅舅急急急急急急叽叽叽叽
        上来了啦啦啦啦啦啦啦啦啦啦啦啦
        skjsl
        的点点滴滴的点点滴滴哒哒哒哒哒哒哒哒哒
        打开看看快快快快快快卡卡卡
        kjslsjls






AA SASA 
SUD：D:KJDLJD:KLDJ:K
当升科技啦啦啦啦啦
手机壳大家
蛇口街道可视角度
会计师快递速度
觉得时间到了

        hdddddddddddddddddei谁红红火火恍恍惚惚哈哈哈
        hssssssssssjk红红火火恍恍惚惚哈哈哈哈哈哈
        还是坎坎坷坷坎坎坷坷看卡
        上看看坎坎坷坷坎坎坷坷
        谁快快快快快快快快快卡卡卡
        
        FontMetrics fontMetrics = textPane.getFontMetrics(font);
        String[] chat_text_list = chat_text.split("\r\n|\r|\n");
        StringBuilder chat_textBuilder = new StringBuilder();
        for (String text : chat_text_list) {
            chat_textBuilder.append(limited_length(text));
        }
        chat_text = chat_textBuilder.toString();
        textPane.setText(chat_text.substring(0, chat_text.length() - 1));
        chat_text_list = chat_text.split("\r\n|\r|\n");
        int rows_number = chat_text_list.length;
        int max_width = fontMetrics.stringWidth(chat_text_list[0]);
        int oneLine_height = fontMetrics.getHeight();
        for (String text : chat_text_list) {
            if (max_width <= fontMetrics.stringWidth(text))
                max_width = fontMetrics.stringWidth(text);
        }
        textPane.setSize(max_width + 10, rows_number * oneLine_height + 10);
        if (who.equals("我")) {
            SetImageIcon.SetIcon(bufferedImage, avatar,
                    oneLine_height + 10, oneLine_height + 10);
            avatar.setSize(oneLine_height + 10, oneLine_height + 10);
            add(Box.createHorizontalStrut(show_pane_width - avatar.getWidth() - textPane.getWidth() - 40));
            add(textPane);
            textPane.setMaximumSize(new Dimension(textPane.getSize()));
            add(Box.createHorizontalStrut(5));
            add(avatar);
            avatar.setMaximumSize(new Dimension(avatar.getSize()));
            add(Box.createHorizontalStrut(5));
        } else {
            SetImageIcon.SetIcon(bufferedImage, avatar,
                    oneLine_height + 10, oneLine_height + 10);
            avatar.setSize(oneLine_height + 10, oneLine_height + 10);
            add(Box.createHorizontalStrut(5));
            add(avatar);
            avatar.setMaximumSize(new Dimension(avatar.getSize()));
            add(Box.createHorizontalStrut(5));
            add(textPane);
            textPane.setMaximumSize(new Dimension(textPane.getSize()));
            add(Box.createHorizontalStrut(show_pane_width - avatar.getWidth() - textPane.getWidth() - 40));
        }
        setSize(780, textPane.getHeight());
    }
    public String limited_length(String chat_texts) {
        String chat_text = "";
        if (chat_texts.length() >= 15) {
            String text1 = chat_texts.substring(0, 15) + "\n";
            String text2 = chat_texts.substring(15);
            chat_text += text1 + limited_length(text2);
        } else {
            chat_text += chat_texts + "\n";
        }
        return chat_text;
    }
}

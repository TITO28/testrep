package blog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Blog�𑀍삷��r�W�l�X���W�b�N
 * 
 * @author Shinji Miyamoto
 */
public class BlogController {

    /**
     * �V���O���g���̃C���X�^���X
     */
    private static BlogController controller = new BlogController();

    /**
     * ���̃N���X�̃C���X�^���X���擾���܂��B
     * 
     * @return BlogController�̃C���X�^���X
     */
    public static BlogController getInstance() {
        return controller;
    }

    private BlogController() {
    }

    /**
     * �g�s�b�N���|�X�g(�o�^)���܂��B
     * 
     * @param topic
     *            �g�s�b�N
     */
    public void postTopic(Topic topic) {
        String sql = "INSERT INTO TOPIC(TITLE, CONTENT)"
                + " VALUES(" + "'" + topic.getTitle() + "'"
                + ",'" + topic.getContent() + "'" + ")";

        Connection con = null;
        Statement smt = null;
        try {
            con = ConnectionManager.getConnection();
            smt = con.createStatement();
            smt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (smt != null) {
                try {
                    smt.close();
                } catch (Exception ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }
    }

    /**
     * �ŋ߂�(�Ƃ������S����)�g�s�b�N���擾���܂��B
     * 
     * @return �g�s�b�N�̃��X�g
     */
    public List<Topic> getTopics() {

        String sql = "SELECT * FROM TOPIC";
        List<Topic> topics = new ArrayList<Topic>();

        Connection con = null;
        Statement smt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            smt = con.createStatement();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getInt("ID"));
                topic.setPostDate(rs
                        .getTimestamp("POST_DATE"));
                topic.setTitle(rs.getString("TITLE"));
                topic.setContent(rs.getString("CONTENT"));
                topics.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ignore) {
                }
            }
            if (smt != null) {
                try {
                    smt.close();
                } catch (Exception ignore) {
                }
            }            
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }

        return topics;
    }

    public static void main(String[] args) {
        BlogController ctrl = BlogController.getInstance();
        List<Topic> topics = ctrl.getTopics();
        for (int i = 0; i < topics.size(); i++) {
            System.out.println(topics.get(i));
        }

        System.out.println("END");
    }

}
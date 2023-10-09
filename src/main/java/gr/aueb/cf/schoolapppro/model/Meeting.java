package gr.aueb.cf.schoolapppro.model;

import java.util.Date;

public class Meeting {

    private Integer teacherId;
    private Integer studentId;
    private String meetingRoom;
    private Date meetingDate;

    public Meeting () {}

    public Meeting(Integer teacherId, Integer studentId, String meetingRoom, Date meetingDate) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "teacherId=" + teacherId +
                ", studentId=" + studentId +
                ", meetingRoom='" + meetingRoom + '\'' +
                ", meetingDate=" + meetingDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meeting meeting = (Meeting) o;

        if (teacherId != meeting.teacherId) return false;
        if (studentId != meeting.studentId) return false;
        if (meetingRoom != null ? !meetingRoom.equals(meeting.meetingRoom) : meeting.meetingRoom != null) return false;
        return meetingDate != null ? meetingDate.equals(meeting.meetingDate) : meeting.meetingDate == null;
    }

    @Override
    public int hashCode() {
        int result = teacherId;
        result = 31 * result + studentId;
        result = 31 * result + (meetingRoom != null ? meetingRoom.hashCode() : 0);
        result = 31 * result + (meetingDate != null ? meetingDate.hashCode() : 0);
        return result;
    }
}

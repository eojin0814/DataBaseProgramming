package model;

public class CommentDTO {
	private int commentNo;
	private int BoardId;
	private String details;
	private CustomerDTO customer;
	private BoardDTO board;
	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}	
	public CommentDTO() {
		super();
		System.out.println("commentDTO");
	}
	
	public BoardDTO getBoard() {
		return board;
	}
	@Override
	public String toString() {
		return "CommentDTO [commentNo=" + commentNo + ", BoardId=" + BoardId + ", details=" + details + ", board="
				+ board + "]";
	}

	public void setBoard(BoardDTO board) {
		this.board = board;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getBoardId() {
		return BoardId;
	}
	public void setBoardId(int boardId) {
		BoardId = boardId;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}

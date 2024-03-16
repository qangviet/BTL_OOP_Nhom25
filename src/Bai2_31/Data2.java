package Bai2_31;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Data2 {
	public static Category Default = new Category("Default");

	public static Category read_file = new Category("File", Default);
	
	public static void setDefault(Category default1) {
		Default = default1;
	}
	public static Category getCourseIT() {
		return CourseIT;
	}
	public static void setCourseIT(Category courseIT) {
		CourseIT = courseIT;
	}
	public static Category getTop_for_IT() {
		return Top_for_IT;
	}
	public static void setTop_for_IT(Category top_for_IT) {
		Top_for_IT = top_for_IT;
	}
	public static Category getC_cau_de() {
		return C_cau_de;
	}
	public static void setC_cau_de(Category c_cau_de) {
		C_cau_de = c_cau_de;
	}
	public static Category getC_linh_tinh() {
		return C_linh_tinh;
	}
	public static void setC_linh_tinh(Category c_linh_tinh) {
		C_linh_tinh = c_linh_tinh;
	}
	public static Category getC_kho() {
		return C_kho;
	}
	public static void setC_kho(Category c_kho) {
		C_kho = c_kho;
	}
	public static Category getCong_nghe_GK2() {
		return Cong_nghe_GK2;
	}
	public static void setCong_nghe_GK2(Category cong_nghe_GK2) {
		Cong_nghe_GK2 = cong_nghe_GK2;
	}
	public static Category getDefault_for_it() {
		return default_for_it;
	}
	public static void setDefault_for_it(Category default_for_it) {
		Data2.default_for_it = default_for_it;
	}
	public static Category getDe() {
		return de;
	}
	public static void setDe(Category de) {
		Data2.de = de;
	}
	public static Category get_20221() {
		return _20221;
	}
	public static void set_20221(Category _20221) {
		Data2._20221 = _20221;
	}
	public static Category getTruoc_den() {
		return truoc_den;
	}
	public static void setTruoc_den(Category truoc_den) {
		Data2.truoc_den = truoc_den;
	}
	public static Category getKho() {
		return kho;
	}
	public static void setKho(Category kho) {
		Data2.kho = kho;
	}
	public static Category getKho_20221() {
		return kho_20221;
	}
	public static void setKho_20221(Category kho_20221) {
		Data2.kho_20221 = kho_20221;
	}
	public static Category getKho_cho_den_20221() {
		return kho_cho_den_20221;
	}
	public static void setKho_cho_den_20221(Category kho_cho_den_20221) {
		Data2.kho_cho_den_20221 = kho_cho_den_20221;
	}
	public static Category getTu_luan_20221() {
		return tu_luan_20221;
	}
	public static void setTu_luan_20221(Category tu_luan_20221) {
		Data2.tu_luan_20221 = tu_luan_20221;
	}
	public static Category getTu_luanKTLT() {
		return tu_luanKTLT;
	}
	public static void setTu_luanKTLT(Category tu_luanKTLT) {
		Data2.tu_luanKTLT = tu_luanKTLT;
	}
	public static Category getFrom_me() {
		return from_me;
	}
	public static void setFrom_me(Category from_me) {
		Data2.from_me = from_me;
	}
	public static Category getThi_lop7() {
		return thi_lop7;
	}
	public static void setThi_lop7(Category thi_lop7) {
		Data2.thi_lop7 = thi_lop7;
	}
	public static Category getSinh_hoc() {
		return sinh_hoc;
	}
	public static void setSinh_hoc(Category sinh_hoc) {
		Data2.sinh_hoc = sinh_hoc;
	}
	public Category getSu_dia() {
		return su_dia;
	}
	public void setSu_dia(Category su_dia) {
		this.su_dia = su_dia;
	}
	public Category getTin_hoc() {
		return tin_hoc;
	}
	public void setTin_hoc(Category tin_hoc) {
		this.tin_hoc = tin_hoc;
	}
	static Category CourseIT = new Category("Course: IT", Default);
	static Category Top_for_IT = new Category("Top for IT", CourseIT);
	static Category C_cau_de = new Category("C câu hỏi dễ", Top_for_IT);
	static Category C_linh_tinh = new Category("C from linh tinh", Top_for_IT);
	static Category C_kho = new Category("Khó", Top_for_IT);
	static Category Cong_nghe_GK2 = new Category("Công nghệ GK2 lớp 7", Top_for_IT);
	static Category default_for_it = new Category("Default for IT", Top_for_IT);
	static Category de = new Category("Dễ", default_for_it);
	static Category _20221 = new Category("20221", de);
	static Category truoc_den = new Category("Trước đến 20211",de);
	static Category kho = new Category("Khó", default_for_it);
	static Category kho_20221 = new Category("Khó 20221", kho);
	static Category kho_cho_den_20221 = new Category("Khó cho đến 20221", kho);
	static Category tu_luan_20221 = new Category("Tự luận 20221", default_for_it);
	static Category tu_luanKTLT = new Category("Tự luận KTLT" , default_for_it);
	static Category from_me = new Category("From me - de thi", Top_for_IT);
	 static Category thi_lop7 = new Category("Thi GK2 lớp 7", Default);
	 static Category sinh_hoc = sampleCategory();
	Category su_dia = new Category("Sử địa GK2 L7", thi_lop7);
	Category tin_hoc = new Category("Tin học GK2 L7", thi_lop7);
	public static Category defaultCategory() {
	
		sinh_hoc.setParent(thi_lop7);

		return Default;
		
	}
	public static Category sampleCategory() {
		Question cau1 = new Question("Cau 1", "Cho nhung dac diem sau thuong moc o nhung noi hoang da: ", FXCollections.observableArrayList(
				new Choice("A."),
				new Choice("B. ", 100.0),
				new Choice("C. ")));
		Question cau2 = new Question("Cau 2", "Cho cac yeu to sau: ", FXCollections.observableArrayList(
				new Choice("A. Anh sang", 100.0),
				new Choice("B. Nhiet do"), 
				new Choice ("C. Ham luong khong khi")));
		Question cau3 = new Question("Cau 3", "Đây là loài cá nào? ", FXCollections.observableArrayList(
				new Choice("a. Nó thuộc loài cá trích, mùa sinh sản di cư từ biển đến sông"),
				new Choice("b. Nó đẻ trứng nơi đầu nguồn là nơi có nước xiết và nhiều ô xy"), 
				new Choice ("c. Cá con sống ở sông từ 1 đến 2 năm rồi về vùng biển ôn đới sống", 100.0)));
		Question cau4 = new Question("Cau 4", "Đây là loài thực vật nào? ", FXCollections.observableArrayList(
				new Choice("a. Một bông của nó là do gần 1000 hoa nhỏ hợp thành"),
				new Choice("b. Là cây lấy dầu rất tốt", 100.0), 
				new Choice("c. Cuống của nó có chất tăng trưởng kỳ diệu nên nó luôn hướng đến mặt trời.")));
		Question cau5 = new Question("Câu 5: Con người ta khát nước vì:");
			
		Question cau6 = new Question("Cau6: Có 4 nhóm máu A, B, O, AB thì người có nhóm máu nào có thể tiếp nhận tất cả các nhóm máu còn lại?");
		
		Question cau7 = new Question("Cau 7: Tôi là loài động vật nào?");
		
		Question cau8 = new Question("Câu 8: Đây là loài hoa nào?");
		
		Question cau9 = new Question("Cau 1", "Cho nhung dac diem sau thuong moc o nhung noi hoang da: ", FXCollections.observableArrayList(
				new Choice("A."),
				new Choice("B. ", 100.0),
				new Choice("C. ")));
		Question cau10 = new Question("Cau 2", "Cho cac yeu to sau: ", FXCollections.observableArrayList(
				new Choice("A. Anh sang", 100.0),
				new Choice("B. Nhiet do"), 
				new Choice ("C. Ham luong khong khi")));
		Question cau11 = new Question("Cau 3", "Đây là loài cá nào? ", FXCollections.observableArrayList(
				new Choice("a. Nó thuộc loài cá trích, mùa sinh sản di cư từ biển đến sông"),
				new Choice("b. Nó đẻ trứng nơi đầu nguồn là nơi có nước xiết và nhiều ô xy"), 
				new Choice ("c. Cá con sống ở sông từ 1 đến 2 năm rồi về vùng biển ôn đới sống", 100.0)));
		Question cau12 = new Question("Cau 4", "Đây là loài thực vật nào? ", FXCollections.observableArrayList(
				new Choice("a. Một bông của nó là do gần 1000 hoa nhỏ hợp thành"),
				new Choice("b. Là cây lấy dầu rất tốt", 100.0), 
				new Choice("c. Cuống của nó có chất tăng trưởng kỳ diệu nên nó luôn hướng đến mặt trời.")));
		Question cau13 = new Question("Cau 1", "Cho nhung dac diem sau thuong moc o nhung noi hoang da: ", FXCollections.observableArrayList(
				new Choice("A."),
				new Choice("B. ", 100.0),
				new Choice("C. ")));
		Question cau14 = new Question("Cau 2", "Cho cac yeu to sau: ", FXCollections.observableArrayList(
				new Choice("A. Anh sang", 100.0),
				new Choice("B. Nhiet do"), 
				new Choice ("C. Ham luong khong khi")));
		Question cau15 = new Question("Cau 3", "Đây là loài cá nào? ", FXCollections.observableArrayList(
				new Choice("a. Nó thuộc loài cá trích, mùa sinh sản di cư từ biển đến sông"),
				new Choice("b. Nó đẻ trứng nơi đầu nguồn là nơi có nước xiết và nhiều ô xy"), 
				new Choice ("c. Cá con sống ở sông từ 1 đến 2 năm rồi về vùng biển ôn đới sống", 100.0)));
		Question cau16 = new Question("Cau 4", "Đây là loài thực vật nào? ", FXCollections.observableArrayList(
				new Choice("a. Một bông của nó là do gần 1000 hoa nhỏ hợp thành"),
				new Choice("b. Là cây lấy dầu rất tốt", 100.0), 
				new Choice("c. Cuống của nó có chất tăng trưởng kỳ diệu nên nó luôn hướng đến mặt trời.")));
		Question cau17 = new Question("Cau 1", "Cho nhung dac diem sau thuong moc o nhung noi hoang da: ", FXCollections.observableArrayList(
				new Choice("A."),
				new Choice("B. ", 100.0),
				new Choice("C. ")));
		Question cau18 = new Question("Cau 2", "Cho cac yeu to sau: ", FXCollections.observableArrayList(
				new Choice("A. Anh sang", 100.0),
				new Choice("B. Nhiet do"), 
				new Choice ("C. Ham luong khong khi")));
		Question cau19 = new Question("Cau 3", "Đây là loài cá nào? ", FXCollections.observableArrayList(
				new Choice("a. Nó thuộc loài cá trích, mùa sinh sản di cư từ biển đến sông"),
				new Choice("b. Nó đẻ trứng nơi đầu nguồn là nơi có nước xiết và nhiều ô xy"), 
				new Choice ("c. Cá con sống ở sông từ 1 đến 2 năm rồi về vùng biển ôn đới sống", 100.0)));
		Question cau20 = new Question("Cau 4", "Đây là loài thực vật nào? ", FXCollections.observableArrayList(
				new Choice("a. Một bông của nó là do gần 1000 hoa nhỏ hợp thành"),
				new Choice("b. Là cây lấy dầu rất tốt", 100.0), 
				new Choice("c. Cuống của nó có chất tăng trưởng kỳ diệu nên nó luôn hướng đến mặt trời.")));
		Category category = new Category("Sinh học GK2 L7", FXCollections.observableArrayList(
				cau1, cau2, cau3, cau4 , cau5, cau6, cau7, cau8, cau9, cau10, 
				cau11, cau12,cau13, cau14, cau15, cau16, cau17, cau18, cau19, cau20));
		
		return category;
	}
	public static ObservableList<Question> sampleListQues() {
		Question cau1 = new Question("Cau 1", "Cho nhung dac diem sau thuong moc o nhung noi hoang da: ", FXCollections.observableArrayList(
				new Choice("A."),
				new Choice("B. ", 100.0),
				new Choice("C. ")));
		Question cau2 = new Question("Cau 2", "Cho cac yeu to sau: ", FXCollections.observableArrayList(
				new Choice("A. Anh sang", 100.0),
				new Choice("B. Nhiet do"), 
				new Choice ("C. Ham luong khong khi")));
		Question cau3 = new Question("Cau 3", "Đây là loài cá nào? ", FXCollections.observableArrayList(
				new Choice("a. Nó thuộc loài cá trích, mùa sinh sản di cư từ biển đến sông"),
				new Choice("b. Nó đẻ trứng nơi đầu nguồn là nơi có nước xiết và nhiều ô xy"), 
				new Choice ("c. Cá con sống ở sông từ 1 đến 2 năm rồi về vùng biển ôn đới sống", 100.0)));
		Question cau4 = new Question("Cau 4", "Đây là loài thực vật nào? ", FXCollections.observableArrayList(
				new Choice("a. Một bông của nó là do gần 1000 hoa nhỏ hợp thành"),
				new Choice("b. Là cây lấy dầu rất tốt", 100.0), 
				new Choice("c. Cuống của nó có chất tăng trưởng kỳ diệu nên nó luôn hướng đến mặt trời.")));
		ObservableList<Question> res = FXCollections.observableArrayList(cau1, cau2, cau3, cau4);
		return res;
 	}

}

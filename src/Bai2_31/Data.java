package Bai2_31;

public class Data {
	static Category Default = new Category("Default");

	public static Category getDefault() {
		return Default;
	}

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
		Data.default_for_it = default_for_it;
	}

	public static Category getDe() {
		return de;
	}

	public static void setDe(Category de) {
		Data.de = de;
	}

	public static Category get_20221() {
		return _20221;
	}

	public static void set_20221(Category _20221) {
		Data._20221 = _20221;
	}

	public static Category getTruoc_den() {
		return truoc_den;
	}

	public static void setTruoc_den(Category truoc_den) {
		Data.truoc_den = truoc_den;
	}

	public static Category getKho() {
		return kho;
	}

	public static void setKho(Category kho) {
		Data.kho = kho;
	}

	public static Category getKho_20221() {
		return kho_20221;
	}

	public static void setKho_20221(Category kho_20221) {
		Data.kho_20221 = kho_20221;
	}

	public static Category getKho_cho_den_20221() {
		return kho_cho_den_20221;
	}

	public static void setKho_cho_den_20221(Category kho_cho_den_20221) {
		Data.kho_cho_den_20221 = kho_cho_den_20221;
	}

	public static Category getTu_luan_20221() {
		return tu_luan_20221;
	}

	public static void setTu_luan_20221(Category tu_luan_20221) {
		Data.tu_luan_20221 = tu_luan_20221;
	}

	public static Category getTu_luanKTLT() {
		return tu_luanKTLT;
	}

	public static void setTu_luanKTLT(Category tu_luanKTLT) {
		Data.tu_luanKTLT = tu_luanKTLT;
	}

	public static Category getFrom_me() {
		return from_me;
	}

	public static void setFrom_me(Category from_me) {
		Data.from_me = from_me;
	}

	public static Category getThi_lop7() {
		return thi_lop7;
	}

	public static void setThi_lop7(Category thi_lop7) {
		Data.thi_lop7 = thi_lop7;
	}

	public static Category getSinh_hoc() {
		return sinh_hoc;
	}

	public static void setSinh_hoc(Category sinh_hoc) {
		Data.sinh_hoc = sinh_hoc;
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
	static Category truoc_den = new Category("Trước đến 20211", de);
	static Category kho = new Category("Khó", default_for_it);
	static Category kho_20221 = new Category("Khó 20221", kho);
	static Category kho_cho_den_20221 = new Category("Khó cho đến 20221", kho);
	static Category tu_luan_20221 = new Category("Tự luận 20221", default_for_it);
	static Category tu_luanKTLT = new Category("Tự luận KTLT", default_for_it);
	static Category from_me = new Category("From me - de thi", Top_for_IT);
	static Category thi_lop7 = new Category("Thi GK2 lớp 7", Default);
	static Category sinh_hoc = new Category("Sinh học", thi_lop7);
	Category su_dia = new Category("Sử địa GK2 L7", thi_lop7);
	Category tin_hoc = new Category("Tin học GK2 L7", thi_lop7);

	public static Category defaultCategory() {

		return Default;

	}

}

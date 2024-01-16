import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class HoaDonBanhMy {
    private String maHoaDon;
    private Date ngayLapHoaDon;
    private String tenKhachHang;
    private String diaChiKhachHang;
    private int soLuongBanCanGiao;
    private double giaBanMotChiec;
    private double tongTienHang;
    private double tienKhuyenMai;
    private double tongTienPhaiThanhToan;

    public HoaDonBanhMy(String maHoaDon, Date ngayLapHoaDon, String tenKhachHang, String diaChiKhachHang,
                        int soLuongBanCanGiao, double giaBanMotChiec) {
        this.maHoaDon = maHoaDon;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.diaChiKhachHang = diaChiKhachHang;
        this.soLuongBanCanGiao = soLuongBanCanGiao;
        this.giaBanMotChiec = giaBanMotChiec;
        tinhTienHang();
        tinhTienKhuyenMai();
        tinhTongTienPhaiThanhToan();
    }

    private void tinhTienHang() {
        tongTienHang = soLuongBanCanGiao * giaBanMotChiec;
    }

    private void tinhTienKhuyenMai() {
        if (soLuongBanCanGiao >= 100) {
            tienKhuyenMai = 0.2 * tongTienHang;
        } else if (soLuongBanCanGiao >= 10) {
            tienKhuyenMai = 0.1 * tongTienHang;
        }
    }

    private void tinhTongTienPhaiThanhToan() {
        tongTienPhaiThanhToan = tongTienHang - tienKhuyenMai;
    }

    public void inHoaDon() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Mã hóa đơn: " + maHoaDon);
        System.out.println("Ngày lập hóa đơn: " + sdf.format(ngayLapHoaDon));
        System.out.println("Tên khách hàng: " + tenKhachHang);
        System.out.println("Địa chỉ khách hàng: " + diaChiKhachHang);
        System.out.println("Số lượng bánh cần giao: " + soLuongBanCanGiao);
        System.out.println("Giá bán 1 chiếc bánh: " + giaBanMotChiec);
        System.out.println("Tổng tiền hàng: " + tongTienHang);
        System.out.println("Tiền khuyến mãi: " + tienKhuyenMai);
        System.out.println("Tổng tiền phải thanh toán: " + tongTienPhaiThanhToan);
    }
}

class QuanLyHoaDon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập thông tin hóa đơn
        System.out.println("Nhập thông tin hóa đơn:");
        System.out.print("Mã hóa đơn: ");
        String maHoaDon = scanner.nextLine();

        System.out.print("Ngày lập hóa đơn (dd/MM/yyyy): ");
        String ngayLapHoaDonStr = scanner.nextLine();
        Date ngayLapHoaDon = null;
        try {
            ngayLapHoaDon = new SimpleDateFormat("dd/MM/yyyy").parse(ngayLapHoaDonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("Tên khách hàng: ");
        String tenKhachHang = scanner.nextLine();

        System.out.print("Địa chỉ khách hàng: ");
        String diaChiKhachHang = scanner.nextLine();

        System.out.print("Số lượng bánh cần giao: ");
        int soLuongBanCanGiao = scanner.nextInt();

        System.out.print("Giá bán 1 chiếc bánh: ");
        double giaBanMotChiec = scanner.nextDouble();

        HoaDonBanhMy hoaDon = new HoaDonBanhMy(maHoaDon, ngayLapHoaDon, tenKhachHang, diaChiKhachHang,
                soLuongBanCanGiao, giaBanMotChiec);

        // In hóa đơn
        System.out.println("\nHóa đơn đã nhập:");
        hoaDon.inHoaDon();

        scanner.close();
    }
}

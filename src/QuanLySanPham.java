import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Product {
    private String prodId;
    private String prodName;
    private String manufacturer;
    private float producerPrice;

    public Product(String prodId, String prodName, String manufacturer, float producerPrice) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.manufacturer = manufacturer;
        this.producerPrice = producerPrice;
    }

    public Product() {
        // Constructor mặc định
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getProducerPrice() {
        return producerPrice;
    }

    public void setProducerPrice(float producerPrice) {
        this.producerPrice = producerPrice;
    }

    public void nhapThongTin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập thông tin sản phẩm:");
        System.out.print("Mã sản phẩm: ");
        prodId = scanner.nextLine();
        System.out.print("Tên sản phẩm: ");
        prodName = scanner.nextLine();
        System.out.print("Nhà sản xuất: ");
        manufacturer = scanner.nextLine();
        System.out.print("Giá sản xuất: ");
        producerPrice = scanner.nextFloat();
    }

    public void hienThiThongTin() {
        System.out.println("Thông tin sản phẩm:");
        System.out.println("Mã sản phẩm: " + prodId);
        System.out.println("Tên sản phẩm: " + prodName);
        System.out.println("Nhà sản xuất: " + manufacturer);
        System.out.println("Giá sản xuất: " + producerPrice);
    }

    public float tinhGiaBan() {
        return producerPrice + 0.05f * producerPrice;
    }
}

class VietNamImportPrice extends Product {
    private float taxImported;

    public VietNamImportPrice(String prodId, String prodName, String manufacturer,
                              float producerPrice, float taxImported) {
        super(prodId, prodName, manufacturer, producerPrice);
        this.taxImported = taxImported;
    }

    public VietNamImportPrice() {
        // Constructor mặc định
    }

    @Override
    public float tinhGiaBan() {
        float giaNhapKhau = getProducerPrice() + taxImported * getProducerPrice();
        return giaNhapKhau + 0.1f * (giaNhapKhau + taxImported * giaNhapKhau);
    }
}

class ThaiLandImportPrice extends Product {
    private float taxImported;
    private float importPriceSupport;

    public ThaiLandImportPrice(String prodId, String prodName, String manufacturer,
                               float producerPrice, float taxImported, float importPriceSupport) {
        super(prodId, prodName, manufacturer, producerPrice);
        this.taxImported = taxImported;
        this.importPriceSupport = importPriceSupport;
    }

    public ThaiLandImportPrice() {
        // Constructor mặc định
    }

    @Override
    public float tinhGiaBan() {
        float giaNhapKhau = getProducerPrice() + taxImported * getProducerPrice() - importPriceSupport * getProducerPrice();
        return giaNhapKhau + 0.05f * (giaNhapKhau + taxImported * giaNhapKhau - importPriceSupport * getProducerPrice());
    }
}

public class QuanLySanPham {
    private ArrayList<VietNamImportPrice> sanPhamVietNam;
    private ArrayList<ThaiLandImportPrice> sanPhamThaiLand;

    public QuanLySanPham() {
        sanPhamVietNam = new ArrayList<>();
        sanPhamThaiLand = new ArrayList<>();
    }

    public void nhapSanPhamVietNam(int n) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            VietNamImportPrice sanPham = new VietNamImportPrice();
            sanPham.nhapThongTin();
            sanPhamVietNam.add(sanPham);
        }
    }

    public void nhapSanPhamThaiLand(int n) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            ThaiLandImportPrice sanPham = new ThaiLandImportPrice();
            sanPham.nhapThongTin();
            sanPhamThaiLand.add(sanPham);
        }
    }

    public void hienThiTatCaSanPham() {
        System.out.println("Sản phẩm Việt Nam:");
        for (VietNamImportPrice sanPham : sanPhamVietNam) {
            sanPham.hienThiThongTin();
        }

        System.out.println("\nSản phẩm Thái Lan:");
        for (ThaiLandImportPrice sanPham : sanPhamThaiLand) {
            sanPham.hienThiThongTin();
        }
    }

    public void sapXepTheoTenSanPham() {
        Collections.sort(sanPhamVietNam, Comparator.comparing(VietNamImportPrice::getProdName));
        Collections.sort(sanPhamThaiLand, Comparator.comparing(ThaiLandImportPrice::getProdName));
    }

    public void hienThiDanhSachSauKhiSapXep() {
        System.out.println("Sắp xếp sản phẩm Việt Nam theo tên:");
        for (VietNamImportPrice sanPham : sanPhamVietNam) {
            sanPham.hienThiThongTin();
        }

        System.out.println("\nSắp xếp sản phẩm Thái Lan theo tên:");
        for (ThaiLandImportPrice sanPham : sanPhamThaiLand) {
            sanPham.hienThiThongTin();
        }
    }

    public void tinhVaHienThiGiaBan() {
        System.out.println("Giá bán cho sản phẩm Việt Nam:");
        for (VietNamImportPrice sanPham : sanPhamVietNam) {
            System.out.println("Tên sản phẩm: " + sanPham.getProdName() + ", Giá bán: " + sanPham.tinhGiaBan());
        }

        System.out.println("\nGiá bán cho sản phẩm Thái Lan:");
        for (ThaiLandImportPrice sanPham : sanPhamThaiLand) {
            System.out.println("Tên sản phẩm: " + sanPham.getProdName() + ", Giá bán: " + sanPham.tinhGiaBan());
        }
    }

    public void timKiemTheoGiaBan(float giaBanCanTim) {
        System.out.println("Kết quả tìm kiếm theo giá bán: " + giaBanCanTim);
        for (VietNamImportPrice sanPham : sanPhamVietNam) {
            if (sanPham.tinhGiaBan() == giaBanCanTim) {
                sanPham.hienThiThongTin();
            }
        }

        for (ThaiLandImportPrice sanPham : sanPhamThaiLand) {
            if (sanPham.tinhGiaBan() == giaBanCanTim) {
                sanPham.hienThiThongTin();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuanLySanPham quanLySanPham = new QuanLySanPham();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Nhập thông tin cho n sản phẩm ở Việt Nam");
            System.out.println("2. Nhập thông tin cho n sản phẩm ở Thái Lan");
            System.out.println("3. Hiển thị tất cả sản phẩm ở Việt Nam và Thái Lan");
            System.out.println("4. Sắp xếp theo tên sản phẩm tăng dần và hiển thị danh sách đã sắp xếp");
            System.out.println("5. Tính và hiển thị giá bán cho sản phẩm ở Việt Nam và Thái Lan");
            System.out.println("6. Tìm kiếm theo giá bán và hiển thị kết quả tìm kiếm");
            System.out.println("7. Thoát");

            System.out.print("Nhập lựa chọn của bạn: ");
            int luaChon = scanner.nextInt();

            switch (luaChon) {
                case 1:
                    System.out.print("Nhập số lượng sản phẩm cần nhập ở Việt Nam: ");
                    int soLuong1 = scanner.nextInt();
                    quanLySanPham.nhapSanPhamVietNam(soLuong1);
                    break;
                case 2:
                    System.out.print("Nhập số lượng sản phẩm cần nhập ở Thái Lan: ");
                    int soLuong2 = scanner.nextInt();
                    quanLySanPham.nhapSanPhamThaiLand(soLuong2);
                    break;
                case 3:
                    quanLySanPham.hienThiTatCaSanPham();
                    break;
                case 4:
                    quanLySanPham.sapXepTheoTenSanPham();
                    quanLySanPham.hienThiDanhSachSauKhiSapXep();
                    break;
                case 5:
                    quanLySanPham.tinhVaHienThiGiaBan();
                    break;
                case 6:
                    System.out.print("Nhập giá bán cần tìm kiếm: ");
                    float giaBanCanTim = scanner.nextFloat();
                    quanLySanPham.timKiemTheoGiaBan(giaBanCanTim);
                    break;
                case 7:
                    System.out.println("Thoát...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}

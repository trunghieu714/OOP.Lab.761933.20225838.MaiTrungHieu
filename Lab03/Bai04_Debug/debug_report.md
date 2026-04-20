# Báo cáo thực hành Debug 

1. Mục tiêu
Sử dụng Debugger để quan sát cách Java truyền tham số và quản lý biến trong bộ nhớ.
2. Các bước thực hiện
- Đặt Breakpoint tại dòng `swap(jungleDVD, cinderellaDVD);` trong lớp `TestPassingParameter`.
- Sử dụng **Step Into (F5)** để đi vào chi tiết hàm.
- Sử dụng cửa sổ **Variables** để theo dõi ID (địa chỉ vùng nhớ) của các đối tượng.

3. Kết quả quan sát
- Khi vào hàm `swap`, Java tạo ra hai biến cục bộ mới là `o1` và `o2`, sao chép địa chỉ từ `jungleDVD` và `cinderellaDVD`.
- Việc thực hiện `o1 = o2` chỉ làm thay đổi địa chỉ mà biến cục bộ `o1` đang trỏ tới, không tác động đến biến `jungleDVD` ở hàm `main`.
- Khi kết thúc hàm, các biến cục bộ `o1`, `o2`, `tmp` bị giải phóng khỏi Stack.

4. Kết luận
Thao tác Debug giúp xác nhận Java hoàn toàn là ngôn ngữ truyền tham trị (Pass-by-value)**. Ngay cả với đối tượng, thứ được truyền đi cũng chỉ là giá trị của tham chiếu (địa chỉ), chứ không phải bản thân tham chiếu đó.

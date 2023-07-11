
<!--
طراحی و پیاده سازی سرویس نظر و امتیازدهی (Review) به محصولات
فرض کنید که ما محصولاتی (Product) داریم که برای مشتریان (User) نمایش داده میشوند. این محصولات مربوط به شرکتهای Provider) مختلفی هستند. مدیر محصولات میتواند هر کدام از محصولات را در معرض نمایش مشتریان قرار دهد برای آنها امکان ثبت نظر (Comment) و امتیاز ( Vote) را به صورت جداگانه فعال یا غیر فعال نمایند همچنین امکان انتخاب اینکه نظر و امتیاز برای این محصولات به صورت عمومی برای تمامی بازدیدکنندگان فعال باشد یا فقط خریداران بتوانند پس از تایید خرید نظر ثبت کنند برای مدیر محصولات باید امکان پذیر باشد.
کاربر وارد صفحه محصولات می.شود. سمت کلاینت این صفحه یک اپلیکیشن SPA است که دیتای لازم برای رندر شدن صفحه را توسط apiهایی از بکاند دریافت میکند قیمت دهی محصولات توسط سرویس استعلام (Enquiry System) انجام میشود و لازم است اطلاعات اینکه هر یک از محصولات نمایش داده شوند یا خیر و امکان ثبت نظر و امتیاز برای آنها فراهم هست یا خیر از سرویس Review دریافت شود. همچنین سه نظر آخر روی هر محصول و میانگین کل امتیازات و تعداد کل نظرات داده شده به هر محصول نیز به کلاینت ارسال میشود تا صفحه را برای کاربر رندر نماید.
در صورتی که امکان ثبت نظر و امتیاز برای کاربر فراهم باشد مشتری میتواند این کار را انجام دهد و نظر و امتیاز او در وضعیت تایید نشده ثبت میگردد. مدیر محصول میتواند نظرات را مشاهده نماید و نظرات و امتیازات تایید نشده را رد یا تایید کند صرفا نظرات و امتیازات تایید شده در میانگین و تعداد نظرات آن
محصول تاثیرگذار هستند.

الف - طراحی ساختار apiهایی که از سمت کلاینت فراخوانی میشوند را انجام دهید. لطفا ذکر کنید که برای رندر شدن صفحه با توجه به توضیحات چند api با چه ورودیهایی call شوند و چه خروجیهایی داشته باشند. همچنین چه سرویس یا سرویسهایی و چگونه در یک اند این apiها را پاسخ میدهد. در این بخش طراحی و معماری سرویس و apiها مدنظر است
ب - با استفاده از Spring Boot بدون استفاده از ابزارهای تولید اپلیکیشن مانند JHipster، سرویس Review را صرفا در سطح یک اند بدون فرانتاند) پیاده سازی کنید. برای خروجی کار ERD سرویس، كد API Doc (ترجیحا (Postman را ارسال نمایید توجه کنید که سرویس، توضیحات ذکر شده را پوشش دهد و نیازی به پیاده سازی سرویس های دیگر مانند سرویس Enquiry نقشهای کاربران و authentication آنها نیست در این بخش کیفیت ساختار پروژه کدنویسی تمیز و کوئریهای بهینه مدنظر
سورس
است)
-->
# product Review Management
## Technologies Used

- Java 17
- Spring Boot 3
- H2 Database
- Swagger 3
## Swagger API Documentation

The API documentation is generated using Swagger 3. To explore the endpoints and interact with the API, follow these steps:

1. Make sure the application is running locally.
2. Open your web browser and navigate to the following URL:
http://localhost:8088/swagger-ui.html


## Design of APIs:

    ### 1- Get Products Details API:
    
* Endpoint: GET /api/v1/products
* Inputs:  
* Outputs:
     *  Product details including name, price, reviewEnabled averageRating, andreviewCount.
     *  Commenting and voting options enabled/disabled for the product.
     *  Public availability of comments and ratings.
     *  Last three comments on the product.
     *  Average total points and total number of comments for the product.
       
     ### 2- Submit Comment and Rating API:
* Endpoint: GET /api/v1/threeLatestReviews
* Inputs:
     * productId (unique identifier for the product).
*  Outputs: Display the last three comments of the desired product according to the last comment registration time.
  
     ### 3- create Comment and Rating API:
* Endpoint: POST /api/v1/review
* Inputs:
     * productId (unique identifier for the product).
     * Review (Review Object).
*  Outputs: None (HTTP 200 OK if the comment is successfully submitted).

     ### 4- Get Unconfirmed Comments API:
* Endpoint: GET /api/v1/unapprovedReviews
* Inputs:  
* Outputs:
     * List of unconfirmed comments and ratings for the product.
     * Each comment should include reviewId, userId, productId comment content, and rating.
       
     ### 5- Approve Comment API:
* Endpoint: PUT /api/v1/approveReview
* Inputs:
     * reviewId (unique identifier for the Review).
* Outputs: None (HTTP 200 OK if the comment is successfully approved).
  
     ### 5- Reject Comment API:
* Endpoint: PUT /api/v1/rejectReview
* Inputs:
     * reviewId (unique identifier for the Review).
* Outputs: None (HTTP 200 OK if the comment is successfully rejected).


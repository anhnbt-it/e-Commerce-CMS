USE [ecommerce_db]
GO

/****** Object:  Table [dbo].[tbl_product_attributes]    Script Date: 4/21/2021 8:48:27 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tbl_products_attributes](
	[product_id] [int] NOT NULL,
	[attribute_id] [int] NOT NULL,
	[value] [nvarchar](255) NOT NULL,
	[created_by] [int] NOT NULL,
	[modified_by] [int] NOT NULL,
	[created_date] [datetime] NULL,
	[modified_date] [datetime] NULL,
 CONSTRAINT [PK_tbl_products_attributes] PRIMARY KEY CLUSTERED 
(
	[product_id] ASC,
	[attribute_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tbl_products_attributes] ADD  CONSTRAINT [DF_tbl_products_attributes_created_by]  DEFAULT ('1') FOR [created_by]
GO
ALTER TABLE [dbo].[tbl_products_attributes] ADD  CONSTRAINT [DF_tbl_products_attributes_modified_by]  DEFAULT ('1') FOR [modified_by]
GO
ALTER TABLE [dbo].[tbl_products_attributes] ADD  CONSTRAINT [DF_tbl_products_attributes_created_date]  DEFAULT (getdate()) FOR [created_date]
GO
ALTER TABLE [dbo].[tbl_products_attributes] ADD  CONSTRAINT [DF_tbl_products_attributes_modified_date]  DEFAULT (getdate()) FOR [modified_date]
GO
ALTER TABLE [dbo].[tbl_products_attributes]  WITH CHECK ADD  CONSTRAINT [FK_tbl_products_attributes_tbl_attributes] FOREIGN KEY([attribute_id])
REFERENCES [dbo].[tbl_attributes] ([id])
GO

ALTER TABLE [dbo].[tbl_products_attributes] CHECK CONSTRAINT [FK_tbl_products_attributes_tbl_attributes]
GO

ALTER TABLE [dbo].[tbl_products_attributes]  WITH CHECK ADD  CONSTRAINT [FK_tbl_products_attributes_tbl_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[tbl_products] ([id])
GO

ALTER TABLE [dbo].[tbl_products_attributes] CHECK CONSTRAINT [FK_tbl_products_attributes_tbl_products]
GO


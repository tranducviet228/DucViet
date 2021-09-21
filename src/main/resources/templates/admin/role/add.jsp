<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chức-năng</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js">
	
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="card shadow mb-4">
			<div class="card-header py-4 d-flex flex-row-reverse">
				<form enctype="multipart/form-data" id="fileUploadForm">
					<button id="btnAddRole" style="width: 60px;" type="button"
						class="btn btn-primary" data-toggle="modal"
						data-target="#exampleModal" title='Thêm chức năng'>
						<span> <i class="fa fa-plus-circle bigger-110 purple"></i>
						</span>
					</button>

					<button style="width: 60px;" id="btnEditRole" type="button"
						class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
						data-toggle="modal" data-target="#exampleModal"
						title='Sửa chức năng'>
						<span> <i class="far fa-edit"></i>
						</span>
					</button>
					<button style="width: 60px;" id="btnDeleteRole" type="button"
						class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
						data-toggle="tooltip" title='Xóa chức năng'>
						<span> <i class="far fa-trash-alt"></i>
						</span>
					</button>


					<div class="modal fade" id="ok" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Thông báo</h4>
								</div>
								<div class="modal-body">
									<p>Thao tác thành công</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>

						</div>
					</div>
					<!-- Modal -->

					<div class="modal fade " id="exampleModal" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog modal-xl" role="document">
							<div class="modal-content">

								<div class="modal-body">
									<div class="card shadow mb-4">

										<div class="card-header py-3">
											<h6 class="m-0 font-weight-bold text-primary">Thêm chức năng
												</h6>
										</div>
										<div class="table">
											<div class="form-row">
												<div class="form-group col-md-6">
													<div class="row" style="padding-top: 20px;">
														<label style="padding-top: 6px; padding-left: 25px;"
															class="col-md-3" for="inputEmail4">Chức năng</label> <input
															name="code" type="text" class="col-md-7 form-control"
															id="code" placeholder="Chức năng">
													</div>
													<input name="id" type="hidden"
														class="col-md-7 form-control" id="id">
												</div>

											
											</div>
										
										
										</div>
									
									</div>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>

									
									<button type="button" id="addRole" class="btn btn-primary">Thêm
										chức năng</button>
									<button type="button" id="editRole" class="btn btn-primary">Sửa
										sửa chức năng</button>
								</div>

							</div>
						</div>
					</div>
				</form>

			</div>


			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-striped" id="dataTable" width="100%"
						cellspacing="0">
						<thead>
							<tr>
								<th class="check-box min-width"
									style="padding-left: 12px; padding-top: 10px; width: 7%">
									<label class="d-flex justify-content-center">Chọn tất
										cả</label>
									<div style="display: flex;">

										<input class="d-flex justify-content-center" type="checkbox"
											style="margin: auto;" name="selectBox" id="checkAll">
									</div>
								</th>
								<th><label class="d-flex justify-content-center">Tên
										chức năng</label></th>
								<th><label class="d-flex justify-content-center">Người 
										tạo</label></th>
								<th><label class="d-flex justify-content-center">Người
										sửa</label></th>
								<th><label class="d-flex justify-content-center">Ngày
										tạo</label></th>

								<th><label class="d-flex justify-content-center">Ngày
										sửa </label></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${lstRoleDTO}" var="item">
								<tr>
									<td class="d-flex justify-content-center get"><input
										id="checkOne" name="checkOne" type="checkbox" value="${item.id}"></td>

									<td id="hide" class="d-flex justify-content-center get"
										style="display: none; visibility: hidden; width: 0px !important; height: 0px !important;">${item.id}</td>

									<td style="text-align: center;">${item.code}</td>
									<td style="text-align: center;">${item.createdBy}</td>	
									<td style="text-align: center;">${item.modifiedBy}</td>
									<td style="text-align: center;">${item.createdDate}</td>
									<td style="text-align: center;">${item.modifiedDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
</body>
</html>
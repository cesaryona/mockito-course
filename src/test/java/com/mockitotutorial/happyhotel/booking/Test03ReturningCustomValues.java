package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Test03ReturningCustomValues {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	@BeforeEach
	public void setup() {
		this.paymentServiceMock = mock(PaymentService.class);
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOMock = mock(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);

		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
	}

	@Test
	public void should_CountAvailablePlaces_When_OneRoomAvailble() {
		Mockito.when(roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 2)));
		int expected = 2;

		int actual = bookingService.getAvailablePlaceCount();

		assertEquals(expected, actual);
	}

	@Test
	public void should_CountAvailablePlaces_When_MultipleRoomsAvailble() {
		List<Room> rooms = Arrays.asList(new Room("Room 1", 2), new Room("Room 2", 5));
		Mockito.when(roomServiceMock.getAvailableRooms()).thenReturn(rooms);
		int expected = 7;

		int actual = bookingService.getAvailablePlaceCount();

		assertEquals(expected, actual);
	}

}
